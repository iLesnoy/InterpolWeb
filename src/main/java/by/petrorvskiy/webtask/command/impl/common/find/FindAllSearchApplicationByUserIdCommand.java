package by.petrorvskiy.webtask.command.impl.common.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class FindAllSearchApplicationByUserIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("execute FindAllSearchApplicationByUserIdCommand ");

        List<SearchApplication> searchApplications;
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        long userId = user.getUserId();
        logger.debug("userId " + userId);
        try {
            searchApplications = applicationService.findApplicationsByUserId(userId);
            router.setPagePath(page);
            request.setAttribute(ParameterAndAttribute.APPLICATIONS, searchApplications);


        } catch (ServiceException e) {
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute FindAllSearchApplicationByUserIdCommand was failed",e);
        }


        return router;
    }
}
