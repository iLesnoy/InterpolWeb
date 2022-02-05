package by.petrorvskiy.webtask.command;

import jakarta.servlet.http.HttpServletRequest;


public interface Command {

    /**
     * Execute.
     *
     * @param request contains information that {@link Command} should processed
     * @return the Router
     */
    Router execute(HttpServletRequest request);
}
