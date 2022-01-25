package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class FindAllApplicationsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final SearchApplicationServiceImpl searchApplicationService = new SearchApplicationServiceImpl();
    @Override
    public Router execute(HttpServletRequest request) {
        List<SearchApplication>searchApplications;
        Router router = new Router();
        HttpSession session = request.getSession();
        String path =(String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);

        logger.debug("execute FindAllApplicationsCommand");

        try {
            searchApplications = searchApplicationService.findAllSearchApplications();
            router.setPagePath(path);

            if(searchApplications.size() > 0){
                request.setAttribute(ParameterAndAttribute.APPLICATIONS,searchApplications);
                request.setAttribute(ParameterAndAttribute.APPLICATION_STATUS,SearchApplication.ApplicationStatus.values());
                logger.debug("SearchApplications "+ searchApplications);
            }else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        } catch (ServiceException e) {
            logger.error("ServiceException in method FindAllApplicationsCommand "+ e.getMessage());
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}