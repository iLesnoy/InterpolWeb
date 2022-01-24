package by.petrorvskiy.webtask.command.impl.add;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.model.dao.impl.MissingPeopleDaoImpl;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.model.service.impl.SearchApplicationServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddMissingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> missingPeopleData = new HashMap<>();
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        String disappearanceDate = request.getParameter(ParameterAndAttribute.DISAPPEARANCE_DATE);


        InputStream stream = null;
        try {
            Part filePart = request.getPart(ParameterAndAttribute.PHOTO);
            stream = filePart.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
            e.printStackTrace();
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
            router.setPagePath(PagePath.ERROR_404);
        }
        return router;
    }


}
