package by.petrorvskiy.webtask.command.impl.admin.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static by.petrorvskiy.webtask.entity.User.Role.ADMIN;


public class UpdateArticleCommand implements Command {


    private static final Logger logger = LogManager.getLogger();
    private final NewsFeedServiceImpl newsFeedService = new NewsFeedServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("UpdateArticleCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> newsData = new HashMap<>();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        long articleId = Long.parseLong(request.getParameter(ParameterAndAttribute.ARTICLE_ID));
        String title = request.getParameter(ParameterAndAttribute.TITLE);
        String newsArticle = request.getParameter(ParameterAndAttribute.NEWS_ARTICLE);


        newsData.put(ParameterAndAttribute.ARTICLE_ID, String.valueOf(articleId));
        newsData.put(ParameterAndAttribute.TITLE, title);
        newsData.put(ParameterAndAttribute.NEWS_ARTICLE, newsArticle);

        try (InputStream stream = request.getPart(ParameterAndAttribute.IMAGE).getInputStream()) {
            if (user.getRole().equals(ADMIN) && newsFeedService.updateArticle(newsData, stream)) {
                String page = request.getContextPath() + PagePath.TO_NEWS_PAGE;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ARTICLE_UPDATED);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute UpdateArticleCommand was failed", e);
        }
        return router;
    }
}

