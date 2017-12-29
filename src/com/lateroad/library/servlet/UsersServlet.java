package com.lateroad.library.servlet;

import com.lateroad.library.bundle.Bundle;
import com.lateroad.library.entity.User;
import com.lateroad.library.exception.ItemNotFoundException;
import com.lateroad.library.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UsersServlet.class);
    private UserService userService;

    public UsersServlet() {
        userService = UserService.getInstance();
        Bundle.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            List<User> userList = userService.getUserList();
            session.setAttribute("users", userList);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqParameter = req.getParameter("action");
        String action = reqParameter.split("_")[0];
        String userID = reqParameter.split("_")[1];
        switch (action) {
            case "delete":
                userService.delete(userID);
                break;
            default:
                LOGGER.error("Undefined action");
                break;
        }
    }
}
