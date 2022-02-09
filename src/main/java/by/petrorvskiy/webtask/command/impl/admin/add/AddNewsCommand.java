package by.petrorvskiy.webtask.command.impl.admin.add;

import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Message;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class AddNewsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> newsData = new HashMap<>();
        String title = request.getParameter(ParameterAndAttribute.TITLE);
        String newsArticle = request.getParameter(ParameterAndAttribute.NEWS_ARTICLE);


        newsData.put(ParameterAndAttribute.TITLE, title);
        newsData.put(ParameterAndAttribute.NEWS_ARTICLE, newsArticle);

        try (InputStream stream = request.getPart(ParameterAndAttribute.IMAGE).getInputStream()){
            if (newsFeedService.addArticle(newsData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.ARTICLE_ADDED);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException: " + e);
            throw new CommandException("Try to execute AddNewsCommand was failed",e);
        }
        return router;
    }


}
