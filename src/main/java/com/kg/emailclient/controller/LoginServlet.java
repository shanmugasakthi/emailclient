package com.kg.emailclient.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kg.emailclient.model.User;

@WebServlet("/")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // //System.out.println("LoginServlet doGet");
        validateUser(req, resp);

    }

    private void validateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("validate user called");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //System.out.println(email + password);

        UserController userController = new UserController();
        //System.out.println("object returned " + userController.getUser(email));
        User user1 = userController.getUser(email);
        if (user1 != null) {
            ServletContext context = getServletContext();
            context.setAttribute("user1", userController.getUser(email));
            //System.out.println("user context " + userController.getUser(email));
            // // req.setAttribute("listBook", "listBook");
            RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
            dispatcher.forward(req, resp);

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }

}