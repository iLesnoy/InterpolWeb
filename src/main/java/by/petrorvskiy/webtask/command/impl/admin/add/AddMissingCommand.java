package by.petrorvskiy.webtask.command.impl.admin.add;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import by.petrorvskiy.webtask.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
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
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        Map<String, String> missingPeopleData = new HashMap<>();
        String name = request.getParameter(ParameterAndAttribute.USER_NAME);
        String surname = request.getParameter(ParameterAndAttribute.USER_SURNAME);
        String disappearanceDate = (request.getParameter(ParameterAndAttribute.DISAPPEARANCE_DATE));

        missingPeopleData.put(ParameterAndAttribute.USER_NAME, name);
        missingPeopleData.put(ParameterAndAttribute.USER_SURNAME, surname);
        missingPeopleData.put(ParameterAndAttribute.DISAPPEARANCE_DATE, disappearanceDate);


        try(InputStream stream = request.getPart(ParameterAndAttribute.PHOTO).getInputStream()){
            if (missingPeopleService.addMissedPeople(missingPeopleData,stream)) {
                String page = request.getContextPath() + PagePath.TO_ADD;
                request.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException: " + e);
            throw new CommandException("Try to execute AddMissingCommand was failed",e);
        }
        return router;
    }
}
