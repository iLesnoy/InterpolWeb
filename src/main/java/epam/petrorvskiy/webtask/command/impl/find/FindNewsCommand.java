package epam.petrorvskiy.webtask.command.impl.find;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.command.Message;
import epam.petrorvskiy.webtask.command.Router;
import epam.petrorvskiy.webtask.model.dao.impl.NewsFeedDaoImpl;
import epam.petrorvskiy.webtask.entity.NewsFeed;
import epam.petrorvskiy.webtask.model.service.NewsFeedService;
import epam.petrorvskiy.webtask.model.service.impl.NewsFeedServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class FindNewsCommand implements Command {

    private NewsFeedService newsFeedService = new NewsFeedServiceImpl(new NewsFeedDaoImpl());


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        NewsFeed newsFeed;
        HttpSession session = request.getSession();
        String articleId = request.getParameter(ParameterAndAttribute.ARTICLE_ID);
        Optional<NewsFeed> optionalNewsFeed;
        try{
                optionalNewsFeed = newsFeedService.takeArticleById(Integer.parseInt(articleId));
                if(optionalNewsFeed.isPresent()){
                    newsFeed = optionalNewsFeed.get();
                    router.setPagePath(PagePath.NEWS_FEED);
                    session.setAttribute(ParameterAndAttribute.ARTICLE_ID,newsFeed);

                }else {
                    router.setPagePath(PagePath.TO_MAIN_PAGE);
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.UNKNOWN_PROBLEM);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        return router;
    }
}
