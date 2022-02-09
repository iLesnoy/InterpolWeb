package by.petrorvskiy.webtask.command.impl.admin.go;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ToUpdateArticleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("execute method ToUpdatePageCommand");

        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_UPDATE_ARTICLE);
        long articleId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));

        try {
            Optional<NewsFeed> newsFeed = newsFeedService.takeArticleById(articleId);
            List<NewsFeed> news = newsFeed.stream().toList();
            request.setAttribute(ParameterAndAttribute.NEWS_ARTICLE, news);
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_500);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute ToUpdateArticleCommand was failed",e);
        }
        router.setPagePath(PagePath.UPDATE_PAGE);
        return router;
    }
}