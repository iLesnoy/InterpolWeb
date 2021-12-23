package epam.task.web.command.impl;

import epam.task.web.command.Command;
import epam.task.web.command.PagePath;
import epam.task.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogOutCommand implements Command {


    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.invalidate();
        router.setPagePath(PagePath.TO_MAIN_PAGE);
        return router;
    }
}
