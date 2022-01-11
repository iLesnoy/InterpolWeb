package epam.petrorvskiy.webtask.command.impl.find;

import com.google.protobuf.ServiceException;
import epam.petrorvskiy.webtask.command.*;
import epam.petrorvskiy.webtask.entity.WantedCriminal;
import epam.petrorvskiy.webtask.model.dao.impl.WantedCriminalDaoImpl;
import epam.petrorvskiy.webtask.model.service.WantedCriminalService;
import epam.petrorvskiy.webtask.model.service.impl.WantedCriminalsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static epam.petrorvskiy.webtask.command.ParameterAndAttribute.CRIMINALS_LIST;

public class FindWantedCriminalsByName implements Command {
    private static final Logger logger = LogManager.getLogger();
    private WantedCriminalService wantedCriminalService = new WantedCriminalsServiceImpl(new WantedCriminalDaoImpl());

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        /*String criminalName = request.getParameter(ParameterAndAttribute.FIRST_NAME);*/
        String criminalName = request.getParameter(ParameterAndAttribute.USER_NAME);

        logger.debug( "find wantedCriminals by name: " + criminalName);
        Optional<WantedCriminal> wantedCriminals;

        try{

            wantedCriminals = wantedCriminalService.findAllCriminalsByName(criminalName);
            router.setPagePath(page);

            if(wantedCriminals.isPresent()){

                request.setAttribute(CRIMINALS_LIST,wantedCriminals);
                logger.debug(request.getAttribute(CRIMINALS_LIST));

            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        }catch (ServiceException e) {
            logger.error("UserServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}