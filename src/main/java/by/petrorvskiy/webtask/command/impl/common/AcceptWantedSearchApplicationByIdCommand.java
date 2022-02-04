package by.petrorvskiy.webtask.command.impl.common;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.validator.DateValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Optional;


public class AcceptWantedSearchApplicationByIdCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("AcceptWantedSearchApplicationByIdCommand ");
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);

        if (user == null) {
            request.setAttribute(ParameterAndAttribute.MESSAGE, Message.PLEASE_LOG_IN);
            router.setPagePath(PagePath.SIGN_UP);
        } else {

            String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
            long userId = user.getUserId();
            long guiltyId = Long.parseLong(request.getParameter(ParameterAndAttribute.GUILTY_ID));
            Optional<Long> applicationId;
            LocalDate leadTime = LocalDate.parse(request.getParameter(ParameterAndAttribute.LEAD_TIME));

            logger.debug("accept search application by user " + userId);

            if (DateValidator.applicationDateValidator(leadTime)) {
                if (duplicateApplicationCheck(userId).isEmpty()) {
                    try {
                        SearchApplication searchApplication = new SearchApplication.ApplicationBuilder()
                                .setLeadTime(leadTime)
                                .setStatus(SearchApplication.ApplicationStatus.PROCESS)
                                .setUserId(userId)
                                .build();
                        applicationService.addSearchApplication(searchApplication);
                        applicationId = applicationService.findApplicationIdByUserId(userId);
                        applicationService.addWantedCriminalApplication(applicationId.get(), guiltyId);

                        router.setPagePath(page);
                        request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_INFO);

                    } catch (ServiceException e) {
                        logger.error("ServiceException in method FindAllSearchApplicationByUserIdCommand " + e.getMessage());
                        request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
                        request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
                        router.setPagePath(PagePath.ERROR_500);
                    }
                } else {
                    request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_ALREADY_ADDED);
                    router.setPagePath(page);
                }
            } else {
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.APPLICATION_VALID_DATE);
                router.setPagePath(page);
            }
        }

        return router;
    }

    private Optional<Long> duplicateApplicationCheck(long userId) {

        Optional<Long> wantedApplicationId = Optional.empty();
        Optional<Long> applicationIdByUserId;
        try {

            applicationIdByUserId = applicationService.findApplicationIdByUserId(userId);
            if (applicationIdByUserId.isPresent()) {
                wantedApplicationId = Optional.of(applicationService.findWantedCriminalGuiltyId(applicationIdByUserId.get()));
            }
        } catch (ServiceException e) {
            logger.warn("duplicateApplicationCheck ServiceException " + e.getMessage());
        }
        return wantedApplicationId;
    }

}