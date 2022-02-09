package by.petrorvskiy.webtask.command.impl.admin.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
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
    private final UserService userService = new UserServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
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
            session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute FindUsersPaginationCommand was failed",e);
        }
        return router;
    }
}
