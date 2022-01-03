package epam.petrorvskiy.webtask.controller;

import java.io.*;

import epam.petrorvskiy.webtask.command.Command;
import epam.petrorvskiy.webtask.command.CommandProvider;
import epam.petrorvskiy.webtask.command.Router;
import epam.petrorvskiy.webtask.model.connection.ConnectionPool;
import epam.petrorvskiy.webtask.command.PagePath;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Command command = CommandProvider.defineCommand(request);
        Router router = command.execute(request);

        switch (router.getType()) {
            case FORWARD:

                logger.debug("forward");
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;

            case REDIRECT:

                logger.debug("redirect");
                response.sendRedirect(router.getPagePath());
                break;

            default:
                logger.error("Incorrect router type:" + router.getType());
                response.sendRedirect(PagePath.MAIN);
        }

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}