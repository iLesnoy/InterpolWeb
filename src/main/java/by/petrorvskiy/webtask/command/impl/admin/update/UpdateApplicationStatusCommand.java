package by.petrorvskiy.webtask.command.impl.admin.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.SearchApplicationService;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class UpdateApplicationStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final SearchApplicationService searchApplicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        boolean isChanged;
        logger.debug("execute method UpdateApplicationStatus");
        long id = Long.parseLong(request.getParameter(ParameterAndAttribute.APPLICATION_ID));
        SearchApplication.ApplicationStatus status = SearchApplication.ApplicationStatus.valueOf(request.getParameter(ParameterAndAttribute.APPLICATION_STATUS));

        try {
            String page = request.getContextPath() + PagePath.TO_ACCOUNT_PAGE;
            isChanged = searchApplicationService.updateSearchApplicationStatus(status, id);

            if (isChanged) {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.SUCCESSFUL);
            } else {
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.UNSUCCESSFUL);
            }
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute UpdateApplicationStatus was failed",e);
        }
        return router;
    }
}
