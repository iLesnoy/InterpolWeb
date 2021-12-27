package epam.task.web.command.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.command.Command;
import epam.task.web.command.PagePath;
import epam.task.web.command.ParameterAndAttribute;
import epam.task.web.controller.Router;
import epam.task.web.dao.impl.NewsFeedDaoImpl;
import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.service.NewsFeedService;
import epam.task.web.service.UserService;
import epam.task.web.service.impl.NewsFeedServiceImpl;
import epam.task.web.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static epam.task.web.command.impl.Message.*;

public class AddNewsCommand  implements Command {

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
                session.setAttribute(MESSAGE_FOR_USER, Message.SUCCESSFUL);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("ServiceException: " + e);
            request.setAttribute(EXCEPTION, "ServiceException");
            request.setAttribute(ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
