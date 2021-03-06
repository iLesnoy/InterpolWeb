package by.petrorvskiy.webtask.command.impl.admin.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllUsersCommand implements Command {
    Logger logger = LogManager.getLogger();
    UserServiceImpl userService = new UserServiceImpl();
    private final int startRow = 0;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("FindAllUsersCommand method");
        int numberOfPages;
        List<User>users;
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);

        try {
            users = userService.findUsersFromRow(startRow);
            numberOfPages = userService.findNumberOfPages();
            router.setPagePath(currentPage);
            request.setAttribute(ParameterAndAttribute.USER_ROLE,User.Role.values());
            request.setAttribute(ParameterAndAttribute.LIST, users);
            session.setAttribute(ParameterAndAttribute.NUMBER_OF_PAGES, numberOfPages);
            session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);

        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute FindAllUsersCommand was failed",e);
        }

        return router;
    }
}
