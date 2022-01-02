package epam.petrorvskiy.webtask.command.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.controller.Router;
import epam.petrorvskiy.webtask.dao.impl.UserDaoImpl;
import epam.petrorvskiy.webtask.entity.SearchApplication;
import epam.petrorvskiy.webtask.entity.User;
import epam.petrorvskiy.webtask.service.UserService;
import epam.petrorvskiy.webtask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static epam.petrorvskiy.webtask.command.PagePath.*;
import static epam.petrorvskiy.webtask.entity.User.Status.BLOCKED;

public class ToAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl(new UserDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ToAccountPage");
        Router router = new Router();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

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
                        /*List<SearchApplication> application = procedureService.findAllActive();*/
                        List<User> user2= userService.findUsersByRole(User.Role.USER);
                        session.setAttribute(ParameterAndAttribute.ACTIVE_APPLICATIONS, user2);
                        /*session.setAttribute(ParameterAndAttribute.ACTIVE_PROCEDURES_LIST, procedures);*/
                        router.setPagePath(PagePath.USER);
                    } catch (ServiceException e) {
                        logger.error( "ServiceException" + e);
                        request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                        request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
                        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR);
                        router.setPagePath(PagePath.ERROR);
                    }
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.USER);
                    break;
                default:

                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_IN_PAGE);
                    router.setPagePath(PagePath.SIGN_IN);
                    break;
            }


        }else {
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_IN_PAGE);
            router.setPagePath(PagePath.SIGN_IN);

        }

        return router;
    }
}
