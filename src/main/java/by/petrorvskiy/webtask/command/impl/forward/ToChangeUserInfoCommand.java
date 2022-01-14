package by.petrorvskiy.webtask.command.impl.forward;

import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
import by.petrorvskiy.webtask.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToChangeUserInfoCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) {
        logger.debug( "execute method ToChangeUserInfoCommand");
        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(ParameterAndAttribute.CURRENT_PAGE, PagePath.TO_PERSONAL_INFO_CHANGE);
        router.setPagePath(PagePath.USER_PERSONAL_INFO_CHANGE);
        return router;
    }
}
