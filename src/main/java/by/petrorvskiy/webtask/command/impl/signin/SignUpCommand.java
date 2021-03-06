package by.petrorvskiy.webtask.command.impl.signin;

import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class SignUpCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> userData = new HashMap<>();
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
        String confirmedPassword = request.getParameter(ParameterAndAttribute.CONFIRMED_PASSWORD);
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        userData.put(ParameterAndAttribute.USER_EMAIL, email);
        userData.put(ParameterAndAttribute.USER_PASSWORD, password);
        userData.put(ParameterAndAttribute.USER_NAME, name);
        userData.put(ParameterAndAttribute.USER_SURNAME, surname);

        if (password.equals(confirmedPassword)) {
            try {
                if (userService.findUserByEmail(email).isEmpty()) {
                    if (userService.addUser(userData)) {

                        String page = request.getContextPath();
                        router.setPagePath(page);
                        router.setType(Router.Type.REDIRECT);
                        request.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);

                    } else {
                        router.setPagePath(PagePath.SIGN_UP);
                        request.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_NOT_ADDED);
                    }
                } else {
                    router.setPagePath(PagePath.SIGN_UP);
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_ALREADY_EXIST);
                }
            } catch (ServiceException e) {
                request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
                router.setPagePath(PagePath.ERROR_500);
                logger.error("ServiceException " + e);
                throw new CommandException("Try to execute SignUpCommand was failed",e);
            }
        } else {
            router.setPagePath(PagePath.SIGN_UP);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOT_MATCHES);
        }
        return router;
    }
}
