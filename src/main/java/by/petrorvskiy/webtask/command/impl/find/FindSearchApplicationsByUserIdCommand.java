package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FindSearchApplicationsByUserIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private SearchApplicationServiceImpl searchApplicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Optional<SearchApplication> optionalSearchApplications;
        List<SearchApplication> searchApplications;
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        long userId = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));


        try {
            optionalSearchApplications = searchApplicationService.findApplicationsByUserId(userId);
            router.setPagePath(page);
            if(optionalSearchApplications.isPresent()){
                searchApplications = optionalSearchApplications.stream().toList();
                request.setAttribute(ParameterAndAttribute.APPLICATIONS,searchApplications);
            }else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }


        } catch (ServiceException e) {
            logger.error("ServiceException in method FindAllSearchApplicationsByUserIdCommand " + e.getMessage());
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }


        return router;
    }
}
