package by.petrorvskiy.webtask.command.impl.add;

import by.petrorvskiy.webtask.model.dao.impl.NewsFeedDaoImpl;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.NewsFeedService;
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

public class AddNewsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private NewsFeedService newsFeedService = new NewsFeedServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        String title = request.getParameter(ParameterAndAttribute.TITLE);
        String newsArticle = request.getParameter(ParameterAndAttribute.NEWS_ARTICLE);


        InputStream stream = null;
        try {
            Part filePart = request.getPart(ParameterAndAttribute.IMAGE);
            stream = filePart.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
            e.printStackTrace();
        }


        newsData.put(ParameterAndAttribute.TITLE, title);
        newsData.put(ParameterAndAttribute.NEWS_ARTICLE, newsArticle);

        try {
            if (newsFeedService.addArticle(newsData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ARTICLE_ADDED);
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