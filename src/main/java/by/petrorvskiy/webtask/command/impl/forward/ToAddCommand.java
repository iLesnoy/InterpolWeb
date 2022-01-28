package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.entity.WantedCriminal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToAddCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("CommandToAdminAddPage");
        Router router = new Router();
        HttpSession session = request.getSession();

        request.setAttribute(ParameterAndAttribute.CRIME_TYPE, WantedCriminal.CrimeType.values());
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_ADD);
        router.setPagePath(PagePath.ADD);
        return router;
    }
}

