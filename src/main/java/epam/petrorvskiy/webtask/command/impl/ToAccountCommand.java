package epam.petrorvskiy.webtask.command.impl;

import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.PagePath;
import epam.petrorvskiy.webtask.command.ParameterAndAttribute;
import epam.petrorvskiy.webtask.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.info( "ToAboutCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_ACCOUNT_PAGE);
        router.setPagePath(PagePath.ACCOUNT);
        return router;
    }
}
