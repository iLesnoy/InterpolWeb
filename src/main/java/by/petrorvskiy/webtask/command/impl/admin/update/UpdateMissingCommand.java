package by.petrorvskiy.webtask.command.impl.admin.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
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

public class UpdateMissingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final MissingPeopleServiceImpl missingPeopleService = new MissingPeopleServiceImpl();


    @Override
    public Router execute(HttpServletRequest request) {
        logger.info("UpdateMissingCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String,String> missingData = new HashMap<>();

        long missingId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));
        String name = request.getParameter(ParameterAndAttribute.FIRST_NAME);
        String surname = request.getParameter(ParameterAndAttribute.LAST_NAME);
        String disappearanceDate = request.getParameter(ParameterAndAttribute.DISAPPEARANCE_DATE);
        InputStream stream = null;
        try {
            Part photo = request.getPart(ParameterAndAttribute.PHOTO);
            stream = photo.getInputStream();
        } catch (IOException | ServletException e) {
            logger.error("ServiceException: " + e);
            e.printStackTrace();
        }

        missingData.put(ParameterAndAttribute.MISSING_ID, String.valueOf(missingId));
        missingData.put(ParameterAndAttribute.FIRST_NAME,name);
        missingData.put(ParameterAndAttribute.LAST_NAME,surname);
        missingData.put(ParameterAndAttribute.DISAPPEARANCE_DATE,disappearanceDate);

        try {
            if(missingPeopleService.updateMissingPeopleById(missingData,stream)){
                String page = request.getContextPath() + PagePath.TO_MISSING_PAGE;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN_UPDATED);
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
