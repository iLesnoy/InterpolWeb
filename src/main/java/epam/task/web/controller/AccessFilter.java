package epam.task.web.controller;

import epam.task.web.utils.PagePath;
import epam.task.web.dao.ColumnName;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "AccessFilter", dispatcherTypes = { DispatcherType.REQUEST,
        DispatcherType.FORWARD }, urlPatterns = "*.jsp")
public class AccessFilter implements Filter {
    private static final Set<String> ALLOWED_GUEST_PATH = new HashSet<>(
            Arrays.asList("/index.jsp", "/jsp/newsfeed.jsp", "/jsp/error.jsp","/jsp/missingpeople.jsp","jsp/newsfeed"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String pagePath = req.getServletPath();
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute(ColumnName.USER) != null);
        boolean allowedPath = ALLOWED_GUEST_PATH.contains(pagePath);

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + PagePath.TO_MAIN_PAGE);
        }
    }
}
