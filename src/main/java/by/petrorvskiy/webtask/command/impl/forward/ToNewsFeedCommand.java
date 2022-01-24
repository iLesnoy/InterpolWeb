package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.entity.NewsFeed;
import by.petrorvskiy.webtask.model.dao.impl.NewsFeedDaoImpl;
import by.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import com.google.protobuf.ServiceException;
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
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<NewsFeed> news = newsFeedServices.findAllNews();
            session.setAttribute(ParameterAndAttribute.NEWS, news);
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_NEWS_PAGE);
            router.setPagePath(PagePath.NEWS_FEED);

        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            logger.error("ServiceException in method execute FindAllNews");
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(PagePath.ERROR_404);
        }
        logger.info( "ToNewsFeedCommand");
        return router;
    }
}
