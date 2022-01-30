package by.petrorvskiy.webtask.command.impl.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UpdateArticleCommand implements Command {


    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("UpdateNewsCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String,String> newsData = new HashMap<>();
        long articleId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));
        String title = request.getParameter(ParameterAndAttribute.TITLE);
        String newsArticle = request.getParameter(ParameterAndAttribute.NEWS_ARTICLE);

        InputStream stream = null;
        try {
            Part photo = request.getPart(ParameterAndAttribute.IMAGE);
            stream = photo.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
            e.printStackTrace();
        }

        newsData.put(ParameterAndAttribute.ARTICLE_ID, String.valueOf(articleId));
        newsData.put(ParameterAndAttribute.TITLE,title);
        newsData.put(ParameterAndAttribute.NEWS_ARTICLE,newsArticle);

        try {
            if(newsFeedService.updateArticle(newsData,stream)){
                String page = request.getContextPath() + PagePath.TO_NEWS_PAGE;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ARTICLE_UPDATED);
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

