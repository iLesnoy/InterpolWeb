package by.petrorvskiy.webtask.command.impl.admin.go;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.MissingPeople;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ToUpdateMissingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug( "execute method ToUpdateMissingCommand");

        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_UPDATE_MISSING);
        long missedId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));

        try {
            Optional<MissingPeople> optionalMissingPeople = missingPeopleService.takeMissedHumanById(missedId);
            List<MissingPeople> missingPeople  = optionalMissingPeople.stream().toList();
            request.setAttribute(ParameterAndAttribute.MISSING_PEOPLE,missingPeople);
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_500);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute ToUpdateMissingCommand was failed",e);
        }

        router.setPagePath(PagePath.UPDATE_PAGE);
        return router;
    }
}
