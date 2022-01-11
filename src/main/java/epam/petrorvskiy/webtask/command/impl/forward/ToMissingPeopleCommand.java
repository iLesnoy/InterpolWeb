package epam.petrorvskiy.webtask.command.impl.forward;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.command.Router;
import epam.petrorvskiy.webtask.entity.MissingPeople;
import epam.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import epam.petrorvskiy.webtask.model.service.MissingPeopleService;
import epam.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ToMissingPeopleCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    MissingPeopleService missingPeopleService = new MissingPeopleServiceImpl(new MissingPeopleDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<MissingPeople> missingPeople = missingPeopleService.findAllMissingPeople();
            logger.info(missingPeople);
            session.setAttribute(ParameterAndAttribute.MISSING_PEOPLE, missingPeople);
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_MISSING_PAGE);
            router.setPagePath(PagePath.MISSING_PEOPLE);

            logger.info("ToMissingPeopleCommand");
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            logger.error("ServiceException in method execute findAllMissingPeople");
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(PagePath.ERROR_404);
        }

        return router;
    }
}
