package by.petrorvskiy.webtask.command.impl.common.go;

import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.petrorvskiy.webtask.command.PagePath.*;
import static by.petrorvskiy.webtask.entity.User.Status.BLOCKED;

public class ToAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("ToAccountPage");
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        logger.info("user " + user);

        if (user != null) {
            if(user.getStatus().equals(BLOCKED)){
                logger.info("Blocked account");
                router.setPagePath(TO_MAIN_PAGE);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ACCOUNT_IS_BLOCKED);
                return router;
            }
            switch (user.getRole()) {
                case ADMIN -> {
                    logger.info("ADMIN account");
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.ADMIN);
                }
                case GUEST -> {
                    logger.info("GUEST account");
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_SIGN_UP_PAGE);
                    router.setPagePath(SIGN_UP);
                }
                case USER -> {
                    logger.info("USER account");
                    try {
                        List<SearchApplication> searchApplications = applicationService.findApplicationsByUserId(user.getUserId());
                        request.setAttribute(ParameterAndAttribute.APPLICATIONS, searchApplications);
                        router.setPagePath(ACCOUNT);

                    } catch (ServiceException e) {
                        logger.error("ServiceException" + e);
                        request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                        request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
                        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
                        router.setPagePath(PagePath.ERROR_404);
                    }
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_ACCOUNT_PAGE);
                    router.setPagePath(PagePath.USER);
                }
                default -> {
                    session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, TO_SIGN_UP_PAGE);
                    router.setPagePath(SIGN_UP);
                }
            }

        } else {
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_SIGN_UP_PAGE);
            router.setPagePath(SIGN_UP);

        }

        return router;
    }
}
