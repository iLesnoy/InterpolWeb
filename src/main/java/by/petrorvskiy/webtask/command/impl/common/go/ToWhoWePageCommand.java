package by.petrorvskiy.webtask.command.impl.common.go;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToWhoWePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug("ToWhoWePageCommand");
        Router router = new Router();
        HttpSession session = request.getSession();

        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_WHO_WE_ARE);
        router.setPagePath(PagePath.WHO_WE_ARE);
        return router;
    }
}