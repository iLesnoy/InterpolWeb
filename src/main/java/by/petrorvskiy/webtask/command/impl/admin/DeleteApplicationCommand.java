package by.petrorvskiy.webtask.command.impl.admin;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static by.petrorvskiy.webtask.command.ParameterAndAttribute.CURRENT_PAGE;


public class DeleteApplicationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();
    WantedCriminalServiceImpl wantedCriminalService = new WantedCriminalServiceImpl();
    NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("execute DeleteArticleCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);

        long applicationId;
        long wantedId;
        long missingId;

        try {
            if (currentPage.equals(PagePath.TO_MISSING_PAGE)) {
                missingId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));
                missingPeopleService.deleteMissedHumanById(missingId);
            } else if (currentPage.equals(PagePath.TO_WANTED_CRIMINALS_PAGE)) {
                wantedId = Long.parseLong(request.getParameter(ParameterAndAttribute.GUILTY_ID));
                wantedCriminalService.deleteWantedCriminal(wantedId);
            } else if (currentPage.equals(PagePath.TO_NEWS_PAGE)) {
                applicationId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));
                newsFeedService.deleteArticleById(applicationId);
            }

            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_DELETED);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute DeleteApplicationCommand was failed",e);
        }

        return router;
    }
}