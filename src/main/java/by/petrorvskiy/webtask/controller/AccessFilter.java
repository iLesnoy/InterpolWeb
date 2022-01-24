package by.petrorvskiy.webtask.controller;

import by.petrorvskiy.webtask.command.PagePath;
import by.petrorvskiy.webtask.command.ParameterAndAttribute;
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
            Arrays.asList("/pages/main.jsp", "/pages/newsfeed.jsp", "/pages/error/error404.jsp","/pages/error/error500.jsp"
                    ,"/pages/wantedcriminals.jsp","/pages/about.jsp","/pages/missing.jsp","/pages/signup.jsp","/pages/login.jsp"));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String pagePath = req.getServletPath();
        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute(ParameterAndAttribute.USER) != null);
        boolean allowedPath = ALLOWED_GUEST_PATH.contains(pagePath);

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + PagePath.TO_MAIN_PAGE);
        }
    }
}
