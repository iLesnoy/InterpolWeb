package by.petrorvskiy.webtask.command.impl.signin;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogOutCommand implements Command {
private final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("LogOutCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        session.invalidate();
        router.setPagePath(PagePath.TO_MAIN_PAGE);
        return router;
    }
}
