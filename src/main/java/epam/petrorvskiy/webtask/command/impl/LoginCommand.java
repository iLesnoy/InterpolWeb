package epam.petrorvskiy.webtask.command.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.controller.Router;
import epam.petrorvskiy.webtask.dao.impl.UserDaoImpl;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.service.UserService;
import epam.petrorvskiy.webtask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserServiceImpl(new UserDaoImpl());


    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        String password = request.getParameter(ParameterAndAttribute.USER_PASSWORD);
        Router router = new Router();
        User user;
        HttpSession session = request.getSession();
        logger.debug( "email: " + email + " password: " + password);
        Optional<User> optionalUser;
        try{
            optionalUser = userService.findUserByEmailAndPassword(email,password);

            if(optionalUser.isPresent()){
                switch (optionalUser.get().getRole()){
                    case  USER:
                        user = optionalUser.get();
                        router.setPagePath(PagePath.TO_MAIN_PAGE);
                        session.setAttribute(ParameterAndAttribute.USER,user);
                        break;

                    case ADMIN:
                        user = optionalUser.get();
                        router.setPagePath(PagePath.ADMIN);
                        session.setAttribute(ParameterAndAttribute.ADMIN,user);
                        logger.info("ADMIN");
                        break;

                    case AGENT:
                        user = optionalUser.get();
                        router.setPagePath(PagePath.TO_USER_PAGE);
                        session.setAttribute(ParameterAndAttribute.USER,user);
                        break;

                    default:
                        logger.error("Incorrect user type:" + optionalUser.isPresent());
                        /*throw */

                }

            } else {
                router.setPagePath(PagePath.SIGN_IN);
                request.setAttribute(ParameterAndAttribute.MESSAGE,Message.INCORRECT_EMAIL_OR_LOGIN);
            }

        } catch (ServiceException e) {
            logger.error("UserServiceException in method execute" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
        }

        return router;
    }
}
