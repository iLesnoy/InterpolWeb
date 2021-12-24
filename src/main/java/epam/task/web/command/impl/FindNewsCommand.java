package epam.task.web.command.impl;

import com.google.protobuf.ServiceException;
import epam.task.web.command.Command;
import epam.task.web.command.PagePath;
import epam.task.web.command.ParameterAndAttribute;
import epam.task.web.controller.Router;
import epam.task.web.dao.impl.NewsFeedDaoImpl;
import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.entity.NewsFeed;
import epam.task.web.entity.User;
import epam.task.web.service.NewsFeedService;
import epam.task.web.service.impl.NewsFeedServiceImpl;
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
                    request.setAttribute(ParameterAndAttribute.MESSAGE,Message.UNKNOWN_PROBLEM);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        return null;
    }
}
