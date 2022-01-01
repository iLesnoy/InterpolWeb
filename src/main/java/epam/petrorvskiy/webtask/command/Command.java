package epam.petrorvskiy.webtask.command;

import epam.petrorvskiy.webtask.controller.Router;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {
    Router execute(HttpServletRequest request);
}
