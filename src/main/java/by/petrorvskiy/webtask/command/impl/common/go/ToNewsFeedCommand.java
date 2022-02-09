package by.petrorvskiy.webtask.command.impl.common.go;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.NewsFeedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.petrorvskiy.webtask.command.PagePath.ERROR_404;

public class ToNewsFeedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    NewsFeedService newsFeedServices = new NewsFeedServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("ToNewsFeedCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        try {
            List<NewsFeed> news = newsFeedServices.findAllNews();
            session.setAttribute(ParameterAndAttribute.NEWS, news);
            if(user!=null) {
                session.setAttribute(ParameterAndAttribute.USER_ROLE, user.getRole());
            }
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_NEWS_PAGE);
            router.setPagePath(PagePath.NEWS_FEED);

        } catch (ServiceException e) {
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e.getMessage());
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(ERROR_404);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute ToNewsFeedCommand was failed",e);
        }
        logger.info( "ToNewsFeedCommand");
        return router;
    }
}
