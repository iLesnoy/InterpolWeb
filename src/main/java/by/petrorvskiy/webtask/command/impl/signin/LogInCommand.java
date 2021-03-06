package by.petrorvskiy.webtask.command.impl.signin;

import by.petrorvskiy.webtask.entity.User;
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
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
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
                        user = optionalUser.get();
                        session.setAttribute(ParameterAndAttribute.USER,user);
                        session.setAttribute(ParameterAndAttribute.USER_PASSWORD,password);
                        router.setPagePath(PagePath.MAIN);
            } else {
                router.setPagePath(PagePath.MAIN);
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.INCORRECT_EMAIL_OR_LOGIN);
            }

        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute LogInCommand was failed",e);
        }

        return router;
    }
}