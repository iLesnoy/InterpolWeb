package by.petrorvskiy.webtask.command.impl.common;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.CURRENT_PAGE;

public class DeleteSearchApplicationByUserIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    SearchApplicationServiceImpl searchApplicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("execute DeleteSearchApplicationByUserIdCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);

        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        long applicationId = Long.parseLong(request.getParameter(ParameterAndAttribute.APPLICATION_ID));


        try {
            searchApplicationService.deleteSearchApplicationByUserId(applicationId,user.getUserId());
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_CONCEALED);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.error("ServiceException in method DeleteSearchApplicationByUserIdCommand " + e.getMessage());
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
        }

        return router;
    }
}
