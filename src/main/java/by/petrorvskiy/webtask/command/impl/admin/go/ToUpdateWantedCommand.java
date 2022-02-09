package by.petrorvskiy.webtask.command.impl.admin.go;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ToUpdateWantedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    WantedCriminalServiceImpl wantedCriminalService = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug( "execute method ToUpdateWantedCommand");

        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_UPDATE_WANTED);
        long wantedId = Long.parseLong(request.getParameter(ParameterAndAttribute.GUILTY_ID));

        try {
            Optional<WantedCriminal> optionalWantedCriminal = wantedCriminalService.takeWantedCriminalById(wantedId);
            List<WantedCriminal> wantedCriminal  = optionalWantedCriminal.stream().toList();
            request.setAttribute(ParameterAndAttribute.WANTED_CRIMINAL,wantedCriminal);
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_500);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute ToUpdateWantedCommand was failed",e);
        }

        router.setPagePath(PagePath.UPDATE_PAGE);
        return router;
    }
}
