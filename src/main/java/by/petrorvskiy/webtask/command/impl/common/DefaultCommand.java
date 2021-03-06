package by.petrorvskiy.webtask.command.impl.common;

import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PagePath.MAIN);
        logger.debug("Unknown command");
        return router;
    }
}
