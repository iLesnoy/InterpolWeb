package by.petrorvskiy.webtask.controller;

import java.io.*;

import by.petrorvskiy.webtask.model.connection.ConnectionPool;
import by.petrorvskiy.webtask.command.Command;
import by.petrorvskiy.webtask.command.CommandProvider;
import by.petrorvskiy.webtask.command.Router;
import by.petrorvskiy.webtask.command.PagePath;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * {@code Controller} class extends functional of {@link HttpServlet}
 * Processes all requests after filtering.
 */

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
@MultipartConfig(maxFileSize = 16777215)
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
            case FORWARD -> {
                logger.debug("forward");
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
            }
            case REDIRECT -> {
                logger.debug("redirect");
                response.sendRedirect(router.getPagePath());
            }
            default -> {
                logger.error("Incorrect router type:" + router.getType());
                response.sendRedirect(PagePath.MAIN);
            }
        }

    }



    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
    
}
