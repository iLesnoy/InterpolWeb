package by.petrorvskiy.webtask.command.impl;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.model.service.SearchApplicationService;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeApplicationStatusToClosedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    SearchApplicationService searchApplicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isChanged;
        logger.debug("execute method ChangeApplicationStatusToClosedCommand");
        long id = Long.parseLong(request.getParameter(ParameterAndAttribute.USER_ID));


        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isChanged = searchApplicationService.updateSearchApplicationStatus(SearchApplication.ApplicationStatus.CLOSED,id);

            if (isChanged) {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.SUCCESSFUL);
            } else {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE_FOR_USER, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            logger.error( "ServiceException in method execute ChangeApplicationStatusToClosedCommand" + e);
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}
