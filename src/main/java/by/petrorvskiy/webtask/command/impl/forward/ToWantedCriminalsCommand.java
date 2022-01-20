package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.model.service.WantedCriminalService;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
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

public class ToWantedCriminalsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    WantedCriminalService wantedCriminalService = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            List<WantedCriminal> wantedCriminals = wantedCriminalService.findAllWantedCriminals();
            session.setAttribute(ParameterAndAttribute.WANTED_CRIMINAL, wantedCriminals);
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_WANTED_CRIMINALS_PAGE);
            router.setPagePath(PagePath.WANTED_CRIMINALS);

            logger.info("ToWantedCriminalsCommand");
        } catch (ServiceException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            logger.error("ServiceException in method execute ToWantedCriminalsCommand");
            session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.ERROR_404);
            router.setPagePath(PagePath.ERROR_404);
        }

        return router;
    }
}
