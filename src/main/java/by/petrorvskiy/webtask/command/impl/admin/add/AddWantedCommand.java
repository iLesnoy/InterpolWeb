package by.petrorvskiy.webtask.command.impl.admin.add;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddWantedCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final WantedCriminalServiceImpl wantedCriminal = new WantedCriminalServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> wantedCriminalsData = new HashMap<>();
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        String crimeCity = request.getParameter(ParameterAndAttribute.CRIME_CITY);
        String dateOfBirth = request.getParameter(ParameterAndAttribute.DATE_OF_BIRTH);
        String reward = request.getParameter(ParameterAndAttribute.REWARD);
        String crimeType = request.getParameter(ParameterAndAttribute.CRIME_TYPE);
        String address = request.getParameter(ParameterAndAttribute.CRIME_ADDRESS);


        wantedCriminalsData.put(ParameterAndAttribute.USER_NAME, name);
        wantedCriminalsData.put(ParameterAndAttribute.USER_SURNAME, surname);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_CITY, crimeCity);
        wantedCriminalsData.put(ParameterAndAttribute.DATE_OF_BIRTH, dateOfBirth);
        wantedCriminalsData.put(ParameterAndAttribute.REWARD, reward);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_TYPE, crimeType);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_ADDRESS, address);


        try (InputStream stream = request.getPart(ParameterAndAttribute.PHOTO).getInputStream() ){
            if (wantedCriminal.addWantedCriminal(wantedCriminalsData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException: " + e);
            throw new CommandException("Try to execute AddWantedCommand was failed",e);
        }
        return router;
    }
}
