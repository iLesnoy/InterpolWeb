package epam.petrorvskiy.webtask.command.impl;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.controller.Router;
import epam.petrorvskiy.webtask.dao.impl.NewsFeedDaoImpl;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.service.NewsFeedService;
import epam.petrorvskiy.webtask.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ToNewsFeedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    NewsFeedService newsFeedServices = new NewsFeedServiceImpl(new NewsFeedDaoImpl());

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
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR);
            router.setPagePath(PagePath.ERROR);
        }
        logger.info( "ToNewsFeedCommand");
        return router;
    }
}
