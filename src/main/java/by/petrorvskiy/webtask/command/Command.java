package by.petrorvskiy.webtask.command;

import by.petrorvskiy.webtask.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


public interface Command {

    /**
     * Execute.
     *
     * @param request contains information that {@link Command} should process
     * @return the Router
     * @throws CommandException
     */
    Router execute(HttpServletRequest request) throws CommandException;
}
