package by.petrorvskiy.webtask.command.impl.signin;

import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LogInCommand implements Command {
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
                        session.setAttribute(ParameterAndAttribute.USER,user);
                        router.setPagePath(PagePath.MAIN);
                        logger.info("USER");
                        break;

                    case ADMIN:
                        user = optionalUser.get();
                        session.setAttribute(ParameterAndAttribute.USER, user);
                        router.setPagePath(PagePath.MAIN);
                        logger.info("ADMIN");
                        break;

                    case AGENT:
                        user = optionalUser.get();
                        session.setAttribute(ParameterAndAttribute.USER, user);
                        router.setPagePath(PagePath.MAIN);
                        logger.info("AGENT");
                        break;

                    default:
                    logger.info("Неверный пароль или почта");

                }

            } else {
                router.setPagePath(PagePath.LOG_IN);
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.INCORRECT_EMAIL_OR_LOGIN);
            }

        } catch (ServiceException e) {
            logger.error("UserServiceException in method execute" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }

        return router;
    }
}