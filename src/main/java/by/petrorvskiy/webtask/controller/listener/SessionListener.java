package by.petrorvskiy.webtask.controller.listener;

import by.petrorvskiy.webtask.command.PagePath;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static by.petrorvskiy.webtask.command.ParameterAndAttribute.*;


@WebListener
public class SessionListener implements ServletContextListener, HttpSessionListener {

    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN);
        session.setAttribute(SESSION_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(LANGUAGE, DEFAULT_LANGUAGE);
    }
}