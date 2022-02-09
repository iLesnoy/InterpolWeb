package by.petrorvskiy.webtask.command.impl.admin.update;

import by.petrorvskiy.webtask.command.*;
import by.petrorvskiy.webtask.exception.CommandException;
import by.petrorvskiy.webtask.exception.ServiceException;
import by.petrorvskiy.webtask.model.service.impl.MissingPeopleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public Router execute(HttpServletRequest request) throws CommandException {
        logger.info("UpdateMissingCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        Map<String, String> missingData = new HashMap<>();

        long missingId = Long.parseLong(request.getParameter(ParameterAndAttribute.MISSING_ID));
        String name = request.getParameter(ParameterAndAttribute.FIRST_NAME);
        String surname = request.getParameter(ParameterAndAttribute.LAST_NAME);
        String disappearanceDate = request.getParameter(ParameterAndAttribute.DISAPPEARANCE_DATE);


        missingData.put(ParameterAndAttribute.MISSING_ID, String.valueOf(missingId));
        missingData.put(ParameterAndAttribute.FIRST_NAME, name);
        missingData.put(ParameterAndAttribute.LAST_NAME, surname);
        missingData.put(ParameterAndAttribute.DISAPPEARANCE_DATE, disappearanceDate);

        try (InputStream stream = request.getPart(ParameterAndAttribute.PHOTO).getInputStream()) {
            if (missingPeopleService.updateMissingPeopleById(missingData, stream)) {
                String page = request.getContextPath() + PagePath.TO_MISSING_PAGE;
                session.setAttribute(ParameterAndAttribute.MESSAGE, Message.MISSING_HUMAN_UPDATED);
                router.setPagePath(page);
                router.setType(Router.Type.REDIRECT);
            }
        } catch (ServiceException | ServletException |IOException e) {
            request.setAttribute(ParameterAndAttribute.EXCEPTION, "ServiceException");
            request.setAttribute(ParameterAndAttribute.ERROR_MESSAGE, e.getMessage());
            router.setPagePath(PagePath.ERROR_500);
            logger.error("ServiceException " + e);
            throw new CommandException("Try to execute UpdateMissingCommand was failed", e);
        }
        return router;
    }


}
