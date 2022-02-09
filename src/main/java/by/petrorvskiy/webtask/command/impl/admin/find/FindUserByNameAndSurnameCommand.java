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

import java.util.Arrays;
import java.util.List;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.LIST;


public class FindUserByNameAndSurnameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();
    private final String SPACE_REGEX = "\\s";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        String fullName = request.getParameter(ParameterAndAttribute.NAME_AND_SURNAME);
        List<String> nameAndSurname = Arrays.asList(fullName.split(SPACE_REGEX));
        List<User> users;
        try {

            users = userService.findUsersByNameAndSurname(nameAndSurname.get(0),nameAndSurname.get(1));
            router.setPagePath(page);

            if (users.size() > 0) {
                request.setAttribute(ParameterAndAttribute.USER_ROLE, User.Role.values());
                request.setAttribute(ParameterAndAttribute.LIST, users);
                logger.debug(request.getAttribute(LIST));
            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.NOT_FOUND);
            }

        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute FindUserByNameAndSurnameCommand was failed",e);
        }
        return router;
    }
}

