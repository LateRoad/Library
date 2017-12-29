package com.lateroad.library.servlet;

import com.lateroad.library.bundle.Bundle;
import com.lateroad.library.entity.User;
import com.lateroad.library.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class IndexServlet extends HttpServlet {
    static {
        new DOMConfigurator().doConfigure("/Users/Roula/Documents/Docs/projects/PSP_Library/src/resources/log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class);

    private UserService userService;

    public IndexServlet() {
        Bundle.getInstance();
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        HttpSession session = req.getSession();
        LOGGER.info("Create session");
        if (session.getAttribute("user") == null) {
            session.setAttribute("inOrOut", "in");
            session.setAttribute("inOrOutLabel", Bundle.getTextBundle().getString("inOrOutLabel"));
            session.setAttribute("inOrOutAdress", "/signin.html");
        }
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        HttpSession session = req.getSession();
        String reqParameter = req.getParameter("action");
        String action = reqParameter.split("_")[0];
        switch (action) {
            case "russian":
                Bundle.setTextBundle(ResourceBundle.getBundle("resources_ru"));
                Bundle.reload(req);
                requestDispatcher.forward(req, resp);
                break;
            case "english":
                Bundle.setTextBundle(ResourceBundle.getBundle("resources_en"));
                Bundle.reload(req);
                requestDispatcher.forward(req, resp);
                break;
            case "register":
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String login = req.getParameter("login");
                String password = req.getParameter("password");

                if (login != null && password != null && name != null && surname != null) {
                    User user = null;
                    user = userService.createUser(name, surname, login, password);
                    if (user != null) {
                        session.setAttribute("user", user);
                        requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                        session.setAttribute("inOrOutLabel", "Выйти");
                        session.setAttribute("inOrOut", "out");
                        session.setAttribute("inOrOutAdress", "/logout.html");
                        requestDispatcher.forward(req, resp);
                        return;
                    }
                }
                resp.sendRedirect("login.html?loginorpassword=invalid");
                break;
            default:
                LOGGER.error("Undefined action");
                break;

        }
    }

}
