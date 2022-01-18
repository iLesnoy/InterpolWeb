package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.entity.SearchApplication;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FindApplicationInformationByIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final SearchApplicationServiceImpl applicationService = new SearchApplicationServiceImpl();
    private final MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();
    private final WantedCriminalServiceImpl wantedCriminalService = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("execute method FindApplicationInformationByIdCommand");
        long missingPeopleId;
        long wantedCriminalId;
        Optional<MissingPeople> optionalMissingPeople;
        Optional<WantedCriminal> optionalWantedCriminal;
        List<MissingPeople> missingPeople;
        List<WantedCriminal> wantedCriminal;

        Router router = new Router();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);

        long applicationId = Long.parseLong(request.getParameter(ParameterAndAttribute.APPLICATION_ID));
        System.out.println(applicationId);

        try {

            router.setPagePath(page);

            missingPeopleId = applicationService.findMissingPeopleId(applicationId);
            wantedCriminalId = applicationService.findWantedCriminalGuiltyId(applicationId);

            optionalMissingPeople = missingPeopleService.takeMissedHumanById(missingPeopleId);
            optionalWantedCriminal = wantedCriminalService.takeWantedCriminalById(wantedCriminalId);

            if (optionalMissingPeople.isPresent() && optionalWantedCriminal.isPresent()) {
                wantedCriminal = optionalWantedCriminal.stream().toList();
                missingPeople = optionalMissingPeople.stream().toList();

                request.setAttribute(ParameterAndAttribute.WANTED_CRIMINAL, wantedCriminal);
                request.setAttribute(ParameterAndAttribute.MISSING_PEOPLE, missingPeople);
                System.out.println(wantedCriminal);
                System.out.println(missingPeople);

            } else if (optionalWantedCriminal.isPresent()) {
                wantedCriminal = optionalWantedCriminal.stream().toList();
                request.setAttribute(ParameterAndAttribute.WANTED_CRIMINAL, wantedCriminal);
                System.out.println(wantedCriminal);

            } else if (optionalMissingPeople.isPresent()) {
                missingPeople = optionalMissingPeople.stream().toList();
                request.setAttribute(ParameterAndAttribute.MISSING_PEOPLE, missingPeople);
                System.out.println(missingPeople);
            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }


        } catch (ServiceException e) {
            logger.error("ServiceException in method wantedCriminalService " + e.getMessage());
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}