package by.petrorvskiy.webtask.command.impl.common.go;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.entity.User;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.WantedCriminalService;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.petrorvskiy.webtask.command.PagePath.ERROR_404;

public class ToWantedCriminalsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    WantedCriminalService wantedCriminalService = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.debug("ToWantedCriminalsCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterAndAttribute.USER);
        try {
            List<WantedCriminal> wantedCriminals = wantedCriminalService.findAllWantedCriminals();
            request.setAttribute(ParameterAndAttribute.WANTED_CRIMINAL, wantedCriminals);
            if(user!=null) {
                session.setAttribute(ParameterAndAttribute.USER_ROLE, user.getRole());
            }
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_WANTED_CRIMINALS_PAGE);
            router.setPagePath(PagePath.WANTED_CRIMINALS);

        } catch (ServiceException e) {
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e.getMessage());
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(ERROR_404);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute ToWantedCriminalsCommand was failed",e);
        }

        return router;
    }
}
