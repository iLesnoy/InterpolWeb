package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.NewsFeed;
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
        public Router execute(HttpServletRequest request) {
            logger.debug( "execute method ToUpdatePageCommand");

            Router router = new Router();
            HttpSession session = request.getSession();
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_UPDATE_ARTICLE);
            long articleId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));

            try {
                Optional<NewsFeed> newsFeed  = newsFeedService.takeArticleById(articleId);
                List<NewsFeed> news  = newsFeed.stream().toList();
                request.setAttribute(ParameterAndAttribute.NEWS_ARTICLE,news);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            router.setPagePath(PagePath.UPDATE_PAGE);
            return router;
        }
    }