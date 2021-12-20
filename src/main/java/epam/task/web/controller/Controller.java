package epam.task.web.controller;

import java.io.*;

import epam.task.web.dao.impl.UserDaoImpl;
import epam.task.web.entity.User;
import epam.task.web.exception.DaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final String email = "admin@admin.com";
    private final String password = "password";

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");




        User user = new User(email,password,name,surname);
        request.setAttribute("user", user);

        UserDaoImpl userDao = new UserDaoImpl();
        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        /*response.sendRedirect("pages/main.jsp");*/
        request.getRequestDispatcher("pages/main.jsp").forward(request, response);
        /*response.sendError(500);*/
    }


    public void destroy() {
    }
}