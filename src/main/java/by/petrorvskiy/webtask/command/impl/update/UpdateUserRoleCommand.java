package by.petrorvskiy.webtask.command.impl.update;

import by.petrorvskiy.webtask.command.*;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UpdateUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isChanged;
        logger.debug("execute method updateUserRole");
        long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));
        User.Role role = User.Role.valueOf(request.getParameter(ParameterAndAttribute.USER_ROLE));


        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isChanged = userService.changeUserRole(id, role);

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
            logger.error("UserServiceException in method execute updateUserRole" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
