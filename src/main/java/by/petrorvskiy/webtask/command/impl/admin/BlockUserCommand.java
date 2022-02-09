package by.petrorvskiy.webtask.command.impl.admin;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.USER_ID;


public class BlockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("BlockUserCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isBlocked;

        long id = Long.parseLong(request.getParameter(USER_ID));

        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isBlocked = userService.updateUserStatusById(id, User.Status.BLOCKED);

            if (isBlocked) {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);

            } else {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute BlockUserCommand was failed",e);
        }
        return router;
    }
}
