package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.CURRENT_PAGE;

public class DeleteArticleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("execute DeleteArticleCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);

        long articleId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));

        try {
            newsFeedService.deleteArticleById(articleId);
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.ARTICLE_DELETED);
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