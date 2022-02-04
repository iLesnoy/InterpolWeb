package by.petrorvskiy.webtask.command.impl.common;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class ChangeUserInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("execute method ChangeUserInfoCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);

        userData.put(ParameterAndAttribute.USER_NAME, name);
        userData.put(ParameterAndAttribute.USER_SURNAME, surname);

        try {
            if (userService.updateUserInfo(user, userData)) {
                String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            logger.info("ServiceException: " + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
        }
        return router;
    }
}