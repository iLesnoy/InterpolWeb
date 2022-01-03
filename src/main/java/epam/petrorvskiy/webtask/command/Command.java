package epam.petrorvskiy.webtask.command;

import jakarta.servlet.http.HttpServletRequest;


public interface Command {
    Router execute(HttpServletRequest request);
}
