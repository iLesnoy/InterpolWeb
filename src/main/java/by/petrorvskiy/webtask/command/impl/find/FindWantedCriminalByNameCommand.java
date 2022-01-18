package by.petrorvskiy.webtask.command.impl.find;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.model.dao.impl.WantedCriminalDaoImpl;
import com.google.protobuf.ServiceException;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import by.petrorvskiy.webtask.model.service.WantedCriminalService;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.CRIMINALS_LIST;

public class FindWantedCriminalByNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private WantedCriminalService wantedCriminalService = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(ParameterAndAttribute.CURRENT_PAGE);
        /*String criminalName = request.getParameter(ParameterAndAttribute.FIRST_NAME);*/
        String criminalName = request.getParameter(ParameterAndAttribute.USER_NAME);

        logger.debug( "find wantedCriminals by name: " + criminalName);
        Optional<WantedCriminal> wantedCriminals;
        List<WantedCriminal> wantedCriminalList;


        try{

            wantedCriminals = wantedCriminalService.findAllCriminalsByName(criminalName);
            wantedCriminalList = wantedCriminals.stream().toList();
            router.setPagePath(page);

            if(wantedCriminals.isPresent()){

                request.setAttribute(CRIMINALS_LIST,wantedCriminalList);
                logger.debug(request.getAttribute(CRIMINALS_LIST));

            } else {
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.ERROR_MESSAGE);
            }

        }catch (ServiceException e) {
            logger.error("ServiceException in method execute");
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }
}