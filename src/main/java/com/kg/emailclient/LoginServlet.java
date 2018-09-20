package com.kg.emailclient;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doGet");
        // validateUser(req, resp);
        String action;

        if (req.getParameter("action") != null) {
            action = req.getParameter("action");
        } else {
            action = "";
        }
        System.out.println(action);
        try {
            switch (action) {
            case "validateuser":
                validateUser(req, resp);
                break;

            case "delete":
                deleteUser(req, resp);
                break;

            case "edit":
                // System.out.println("case= edit");
                showEditForm(req, resp);
                break;

            case "insert":
                showAddForm(req, resp);
                break;

            case "saveorupdate":
                saveorupdateUser(req, resp);
                break;

            default:
                listUser(req, resp);
                break;
            }
        } catch (ServletException | IOException e) {

            e.printStackTrace();
        }

    }

    private void validateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("validate user called");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(email + password);

        UserController userController = new UserController();
        System.out.println("object returned "+userController.getUser(email));
        User user1=userController.getUser(email);
        if (user1!=null) {
        ServletContext context = getServletContext();
        context.setAttribute("user1",userController.getUser(email) );
        System.out.println("user context "+userController.getUser(email));
        //     // req.setAttribute("listBook", "listBook");
            RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listBook", "listBook");
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void saveorupdateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}