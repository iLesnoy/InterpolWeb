package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import by.petrorvskiy.webtask.model.service.MissingPeopleService;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
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
