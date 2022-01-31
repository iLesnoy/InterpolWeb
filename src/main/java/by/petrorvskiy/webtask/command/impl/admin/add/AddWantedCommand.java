package by.petrorvskiy.webtask.command.impl.admin.add;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.WantedCriminalServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
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
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> wantedCriminalsData = new HashMap<>();
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        String crimeCity = request.getParameter(ParameterAndAttribute.CRIME_CITY);
        String dateOfBirth = request.getParameter(ParameterAndAttribute.DATE_OF_BIRTH);
        String reward = request.getParameter(ParameterAndAttribute.REWARD);
        String crimeType = request.getParameter(ParameterAndAttribute.CRIME_TYPE);
        String address = request.getParameter(ParameterAndAttribute.CRIME_ADDRESS);



        InputStream stream = null;
        try {
            Part photo = request.getPart(ParameterAndAttribute.PHOTO);
            stream = photo.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
            e.printStackTrace();
        }


        wantedCriminalsData.put(ParameterAndAttribute.USER_NAME, name);
        wantedCriminalsData.put(ParameterAndAttribute.USER_SURNAME, surname);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_CITY, crimeCity);
        wantedCriminalsData.put(ParameterAndAttribute.DATE_OF_BIRTH, dateOfBirth);
        wantedCriminalsData.put(ParameterAndAttribute.REWARD, reward);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_TYPE, crimeType);
        wantedCriminalsData.put(ParameterAndAttribute.CRIME_ADDRESS, address);


        try {
            if (wantedCriminal.addWantedCriminal(wantedCriminalsData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("ServiceException: " + e);
            request.setAttribute(Message.EXCEPTION, "ServiceException");
            request.setAttribute(Message.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR_500);
        }
        return router;
    }
}
