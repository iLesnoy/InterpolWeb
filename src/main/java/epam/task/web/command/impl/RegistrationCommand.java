package epam.task.web.command.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.command.Command;
import epam.task.web.command.PagePath;
import epam.task.web.command.ParameterAndAttribute;
import epam.task.web.command.RequestParameter;
import epam.task.web.controller.Router;
import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.entity.User;
import epam.task.web.service.UserService;
import epam.task.web.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static epam.task.web.command.RequestParameter.*;

public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        User user;
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
/*
        String confirmedPassword = request.getParameter(ParameterAndAttribute.CONFIRMED_PASSWORD);
*/
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        userData.put(EMAIL_PARAMETER, email);
        userData.put(PASSWORD_PARAMETER, password);
        userData.put(USER_NAME, name);
        userData.put(USER_SURNAME, surname);
        try {
            if(userService.findUserIdByEmail(email).isEmpty()) {
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
            }else {
                router.setPagePath(PagePath.SIGN_UP);
                request.setAttribute(ParameterAndAttribute.MESSAGE,Message.USER_ALREDY_EXIST);
            }
        }catch (ServiceException e){
            logger.error("Registration command exception"+ e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
        }

        return null;
    }
}
