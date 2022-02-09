package by.petrorvskiy.webtask.command.impl.admin;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.UserService;
import by.petrorvskiy.webtask.model.service.impl.UserServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.USER_ID;
import static by.petrorvskiy.webtask.entity.User.Status.ACTIVE;


public class UnblockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("execute UnblockUserCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isBlocked;

        long id = Long.parseLong(request.getParameter(USER_ID));

        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isBlocked = userService.updateUserStatusById(id,ACTIVE);

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
            throw new CommandException("Try to execute UnblockUserCommand was failed",e);
        }
        return router;
    }
}