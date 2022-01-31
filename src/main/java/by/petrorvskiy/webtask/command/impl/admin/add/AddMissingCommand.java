package by.petrorvskiy.webtask.command.impl.admin.add;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
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

public class AddMissingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> missingPeopleData = new HashMap<>();
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        String disappearanceDate = (request.getParameter(ParameterAndAttribute.DISAPPEARANCE_DATE));


        InputStream stream = null;
        try {
            Part photo = request.getPart(ParameterAndAttribute.PHOTO);
            stream = photo.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
        }


        missingPeopleData.put(ParameterAndAttribute.USER_NAME, name);
        missingPeopleData.put(ParameterAndAttribute.USER_SURNAME, surname);
        missingPeopleData.put(ParameterAndAttribute.DISAPPEARANCE_DATE, disappearanceDate);


        try {
            if (missingPeopleService.addMissedPeople(missingPeopleData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN);
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
