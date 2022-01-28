package by.petrorvskiy.webtask.command.impl.common;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.validator.LocaleValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static by.petrorvskiy.webtask.command.ParameterAndAttribute.*;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String language = request.getParameter(LANGUAGE);


        logger.debug( "Locale parameter is " + language);
        if (LocaleValidator.isLocaleExist(language)) {
            session.setAttribute(SESSION_LOCALE, language);
        } else {
            logger.warn( "Wrong locale parameter: " + language);
        }
        router.setPagePath(currentPage);
        return router;
    }
}
