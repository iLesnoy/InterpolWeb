package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserRoleToUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isChanged;
        logger.debug("execute method ChangeUserRoleToUserCommand");
        long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));


        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isChanged = userService.changeUserRole(id, User.Role.USER);

            if (isChanged) {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
            } else {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            logger.error( "UserServiceException in method execute ChangeUserRoleToUserCommand" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
