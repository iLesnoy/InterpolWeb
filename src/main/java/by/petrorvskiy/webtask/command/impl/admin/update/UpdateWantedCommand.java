package by.petrorvskiy.webtask.command.impl.admin.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class UpdateWantedCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final WantedCriminalServiceImpl wantedCriminalService = new WantedCriminalServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("UpdateWantedCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> wantedData = new HashMap<>();

        long guiltyId = Long.parseLong(request.getParameter(ParameterAndAttribute.GUILTY_ID));
        String name = request.getParameter(ParameterAndAttribute.FIRST_NAME);
        String surname = request.getParameter(ParameterAndAttribute.LAST_NAME);
        String crimeCity = request.getParameter(ParameterAndAttribute.CRIME_CITY);
        String crimeAddress = request.getParameter(ParameterAndAttribute.CRIME_ADDRESS);
        String dateOfBirth = request.getParameter(ParameterAndAttribute.DATE_OF_BIRTH);
        String reward = request.getParameter(ParameterAndAttribute.REWARD);
        String crimeType = request.getParameter(ParameterAndAttribute.CRIME_TYPE);


        wantedData.put(ParameterAndAttribute.GUILTY_ID, String.valueOf(guiltyId));
        wantedData.put(ParameterAndAttribute.FIRST_NAME, name);
        wantedData.put(ParameterAndAttribute.LAST_NAME, surname);
        wantedData.put(ParameterAndAttribute.CRIME_CITY, crimeCity);
        wantedData.put(ParameterAndAttribute.CRIME_ADDRESS, crimeAddress);
        wantedData.put(ParameterAndAttribute.DATE_OF_BIRTH, dateOfBirth);
        wantedData.put(ParameterAndAttribute.REWARD, reward);
        wantedData.put(ParameterAndAttribute.CRIME_TYPE, crimeType);


        try (InputStream stream = request.getPart(ParameterAndAttribute.PHOTO).getInputStream()) {
            if (wantedCriminalService.updateWantedCriminalById(wantedData, stream)) {
                String page = request.getContextPath() + PagePath.TO_WANTED_CRIMINALS_PAGE;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.WANTED_CRIMINAL_UPDATED);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        }catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute UpdateWantedCommand was failed", e);
        }
        return router;
    }


}
