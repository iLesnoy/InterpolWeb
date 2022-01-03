package epam.petrorvskiy.webtask.command.impl.signing;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.Message;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import epam.petrorvskiy.webtask.command.Router;
import epam.petrorvskiy.webtask.model.service.UserService;
import epam.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SignUpCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
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
                if (userService.findUserIdByEmail(email).isEmpty()) {
                    if (userService.addUser(userData)) {
                        String page = request.getContextPath();
                        router.setPagePath(page);
                        router.setType(Router.Type.REDIRECT);
                        /*session.setAttribute(EMAIL_CHECK);*/
                        return new Router(PagePath.TO_MAIN_PAGE);
                    } else {
                        router.setPagePath(PagePath.SIGN_IN);
                        request.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_NOT_ADDED);
                    }
                } else {

                    router.setPagePath(PagePath.SIGN_UP);
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.USER_ALREADY_EXIST);
                }
            } catch (ServiceException e) {
                logger.error("Registration command exception" + e);
                request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
                router.setPagePath(PagePath.ERROR);
            }
        }else {
            router.setPagePath(PagePath.SIGN_UP);
            request.setAttribute(ParameterAndAttribute.MESSAGE,"password and confirmed password don't match");
        }

            return router;
        }
    }
