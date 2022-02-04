package by.petrorvskiy.webtask.command.impl.admin.find;

import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.LIST;

public class FindUsersByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        String userName = request.getParameter(ParameterAndAttribute.USER_NAME);

        logger.debug("find user by name: " + userName);
        List<User> users;
        try {

            users = userService.findUsersByName(userName);
            router.setPagePath(page);

            if (users.size() > 0) {
                request.setAttribute(ParameterAndAttribute.USER_ROLE, User.Role.values());
                request.setAttribute(ParameterAndAttribute.LIST, users);
                logger.debug(request.getAttribute(LIST));

            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        } catch (ServiceException e) {
            logger.error("ServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
        }
        return router;
    }
}
