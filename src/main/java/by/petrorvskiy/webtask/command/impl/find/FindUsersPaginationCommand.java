package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindUsersPaginationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug( "FindUsersPaginationCommand");
        List<User> users;
        Router router = new Router();
        int pageNumber = Integer.parseInt(request.getParameter(ParameterAndAttribute.START_FROM));
        logger.debug("page number:" + pageNumber);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        try {
            users = userService.findUsersFromRow(pageNumber);
            router.setPagePath(page);
            request.setAttribute(ParameterAndAttribute.LIST, users);
            session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
        } catch (ServiceException e) {
            logger.error( "UserServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
