package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.USER_ID;

public class BlockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("BlockUserCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isBlocked;

        long id = Long.parseLong(request.getParameter(USER_ID));

        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isBlocked = userService.updateUserStatusById(id, User.Status.BLOCKED);

            if (isBlocked) {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);

            } else {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            logger.error( "UserServiceException in method execute BlockUserCommand" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
