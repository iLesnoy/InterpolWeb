package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Stream;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.LIST;

public class FindUserByNameAndSurnameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        String fullName = request.getParameter(ParameterAndAttribute.USER_FULLNAME);
        List<String> nameAndSurname = Stream.of(fullName.split("\\s")).toList();
        List<User> users;
        try {

            users = userService.findUsersByNameAndSurname(nameAndSurname.get(0),nameAndSurname.get(1));
            router.setPagePath(page);

            if (users.size() > 0) {
                request.setAttribute(ParameterAndAttribute.USER_ROLE, User.Role.values());
                request.setAttribute(ParameterAndAttribute.LIST, users);
                logger.debug(request.getAttribute(LIST));

            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        } catch (ServiceException e) {
            logger.error("UserServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}

