package by.petrorvskiy.webtask.command.impl.admin.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class FindSearchApplicationsByUserIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private SearchApplicationServiceImpl searchApplicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("execute FindSearchApplicationsByUserIdCommand");
        List<SearchApplication> searchApplications;
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));


        try {
            searchApplications = searchApplicationService.findApplicationsByUserId(userId);
            logger.debug("searchApplication "+ searchApplications);
            router.setPagePath(page);

        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute FindAllSearchApplicationsByUserIdCommand was failed",e);
        }
        return router;
    }
}
