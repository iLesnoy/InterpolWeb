package by.petrorvskiy.webtask.command.impl.forward;

import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.dao.impl.UserDaoImpl;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.petrorvskiy.webtask.command.PagePath.*;
import static by.petrorvskiy.webtask.entity.User.Status.BLOCKED;

public class ToAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ToAccountPage");
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        logger.info("user " + user);

        if (user != null ) {
            if(user.getStatus().equals(BLOCKED)){
                logger.info("Blocked account");
                router.setPagePath(TO_MAIN_PAGE);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ACCOUNT_IS_BLOCKED);
                return router;
            }
            switch (user.getRole()) {
                case ADMIN:
                    logger.info("ADMIN account");

                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.ADMIN);
                    break;
                case AGENT:
                    logger.info("AGENT account");
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.AGENT);
                    break;
                case USER:
                    logger.info("USER account");
                    try {
                        
                        List<User> user2= userService.findUsersByRole(User.Role.USER);
                        session.setAttribute(ParameterAndAttribute.ACTIVE_APPLICATIONS, user2);
                        
                        router.setPagePath(PagePath.USER);
                    } catch (ServiceException e) {
                        logger.error( "ServiceException" + e);
                        request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                        request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
                        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
                        router.setPagePath(PagePath.ERROR_404);
                    }
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.USER);
                    break;
                default:

                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_SIGN_UP_PAGE);
                    router.setPagePath(SIGN_UP);
                    break;
            }


        } else {
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_UP_PAGE);
            router.setPagePath(SIGN_UP);

        }

        return router;
    }
}
