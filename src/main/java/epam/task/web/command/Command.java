package epam.task.web.command;

import epam.task.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {
    Router execute(HttpServletRequest request);
}
