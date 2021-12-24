package epam.task.web.command.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.command.Command;
import epam.task.web.command.ParameterAndAttribute;
import epam.task.web.controller.Router;
import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.entity.User;
import epam.task.web.service.UserService;
import epam.task.web.service.impl.UserServiceImpl;
import epam.task.web.command.PagePath;
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
        Router router = new Router();
        User user;
        HttpSession session = request.getSession();
        String email = request.getParameter(ParameterAndAttribute.USER_EMAIL);
        long password = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_PASSWORD));
        Optional<User> optionalUser;

        try{
            optionalUser = userService.findUserByEmailAndPassword(email,password);

            if(optionalUser.isPresent()){
                user = optionalUser.get();
                router.setPagePath(PagePath.SIGN_IN);
                session.setAttribute(ParameterAndAttribute.USER,user);
            }else {
                router.setPagePath(PagePath.TO_MAIN_PAGE);
                request.setAttribute(ParameterAndAttribute.MESSAGE,Message.INCORRECT_EMAIL_OR_LOGIN);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return null;
    }
}
