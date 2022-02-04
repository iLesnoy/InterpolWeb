package by.petrorvskiy.webtask.command.impl.common.go;

import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.model.service.NewsFeedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ToNewsFeedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    NewsFeedService newsFeedServices = new NewsFeedServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
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
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            logger.error("ServiceException in method execute findAllNews");
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(PagePath.ERROR_404);
        }
        logger.info( "ToNewsFeedCommand");
        return router;
    }
}
