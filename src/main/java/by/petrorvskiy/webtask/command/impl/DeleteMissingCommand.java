package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.CURRENT_PAGE;

public class DeleteMissingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("execute DeleteArticleCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);

        long missingId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));

        try {
            missingPeopleService.deleteMissedHumanById(missingId);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_DELETED);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.error("ServiceException in method DeleteArticleCommand " + e.getMessage());
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
        }

        return router;
    }
}