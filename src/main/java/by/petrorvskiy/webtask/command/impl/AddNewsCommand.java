package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.model.dao.impl.NewsFeedDaoImpl;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.NewsFeedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AddNewsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private NewsFeedService newsFeedService = new NewsFeedServiceImpl(new NewsFeedDaoImpl());


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        String articleId = request.getParameter(ParameterAndAttribute.ARTICLE_ID);
        String newsArticle = request.getParameter(ParameterAndAttribute.NEWS_ARTICLE);
        String userId = request.getParameter(ParameterAndAttribute.USER_ID);
        newsData.put(ParameterAndAttribute.ARTICLE_ID, articleId);
        newsData.put(ParameterAndAttribute.NEWS_ARTICLE, newsArticle);
        newsData.put(ParameterAndAttribute.USER_ID, userId);
        try {
            if (newsFeedService.addArticle(newsData)) {
                System.out.println(newsData);
                String page = request.getContextPath() + PagePath.NEWS_FEED;
                session.setAttribute(Message.MESSAGE_FOR_USER, Message.SUCCESSFUL);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("ServiceException: " + e);
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
