package by.petrorvskiy.webtask.command.impl.admin;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Optional;

public class AcceptMissingSearchApplicationByIdCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("AcceptMissingSearchApplicationByIdCommand ");
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

        if (user == null) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.PLEASE_LOG_IN);
            router.setPagePath(PagePath.SIGN_UP);
        } else {

            String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
            long userId = user.getUserId();
            long missingId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));
            Optional<Long> applicationId;
            LocalDate leadTime = LocalDate.parse(request.getParameter(ParameterAndAttribute.LEAD_TIME));

            logger.debug("accept search application by user " + userId);

            if (duplicateApplicationCheck(userId).isEmpty()) {
                try {
                    SearchApplication searchApplication = new SearchApplication.ApplicationBuilder()
                            .setLeadTime(leadTime).setStatus(SearchApplication.ApplicationStatus.PROCESS)
                            .setUserId(userId).build();
                    applicationService.addSearchApplication(searchApplication);
                    applicationId = applicationService.findApplicationIdByUserId(userId);
                    applicationService.addMissingCriminalApplication(applicationId.get(), missingId);

                    router.setPagePath(page);
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_INFO);

                } catch (ServiceException e) {
                    logger.error("ServiceException in method AcceptMissingSearchApplicationByIdCommand " + e.getMessage());
                    request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                    request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
                    router.setPagePath(PagePath.ERROR_500);
                }
            } else {
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_ALREADY_ADDED);
                router.setPagePath(page);
            }
        }

        return router;
    }

    private Optional<Long> duplicateApplicationCheck(long userId) {
        Optional<Long> missingApplicationId = Optional.empty();
        Optional<Long> applicationIdByUserId;
        try {
            applicationIdByUserId = applicationService.findApplicationIdByUserId(userId);
            if (applicationIdByUserId.isPresent()) {
                missingApplicationId = Optional.of(applicationService.findMissingPeopleId(applicationIdByUserId.get()));
            }

        } catch (ServiceException e) {
            logger.warn("duplicateApplicationCheck ServiceException " + e.getMessage());
        }
        return missingApplicationId;
    }
}