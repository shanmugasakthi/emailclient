package com.kg.emailclient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kg.emailclient.model.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

    ArrayList<User> userList = new ArrayList<User>();
    // public List<User> userList = Arrays.asList(new User(1, "email", "password"),
    // new User(2, "email2", "password2"),
    // new User(3, "email3", "password3"));
    private String mode = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("UserController called");
        String forward = "";
        String action = req.getParameter("action");
        System.out.println(action);
        try {
            switch (action) {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // userList.add(new User(1, "Apple"));
        // userList.add(new User(2, "Orange"));
        // userList.add(new User(3, "Mango"));
        System.out.println("userList display");
        req.setAttribute("userList", userList);
        RequestDispatcher view = req.getRequestDispatcher("userlist.jsp");
        view.forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("showAddForm called");
        RequestDispatcher view = req.getRequestDispatcher("user.jsp");
        view.forward(req, resp);

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("showEditForm called");
        mode = "update";
        int userid = Integer.parseInt(req.getParameter("userid"));
        System.out.println(userid);

        User updateUser = new User();

        for (User user : userList) {
            if (user.getUserid() == userid) {
                updateUser = user;
                break;
            }

        }
        req.setAttribute("user", updateUser);
        RequestDispatcher view = req.getRequestDispatcher("user.jsp");
        view.forward(req, resp);
    }

    private void saveorupdateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("saveorupdateUser called");

        if (mode != "update") {

            int userid = Integer.parseInt(req.getParameter("userid"));
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User newUser = new User(userid, email, password);
            userList.add(newUser);

            // req.setAttribute("userList", userList);
        } else {
            System.out.println("edit user called arraylist to be updated here");

            int userid = Integer.parseInt(req.getParameter("userid"));
            System.out.println(userid);
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User updateUser = new User(userid, email, password);

            for (User user : userList) {
                if (user.getUserid() == userid) {
                    userList.set(userList.indexOf(user), updateUser);
                    mode = "";
                    break;
                }

            }
            
        }

        req.setAttribute("userList", userList);
        RequestDispatcher view = req.getRequestDispatcher("userlist.jsp");
        view.forward(req, resp);

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userid = Integer.parseInt(req.getParameter("userid"));
        for (User user : userList) {
            if (user.getUserid() == userid) {

                System.out.println(user);
                userList.remove(userList.indexOf(user));
                break;
            }

        }

        req.setAttribute("userList", userList);
        RequestDispatcher view = req.getRequestDispatcher("userlist.jsp");
        view.forward(req, resp);
    }

    User getUser(String email) {
        System.out.println("user list" + userList);
        System.out.println("email  " + email);
        User exuser = new User();
        // boolean validuser = false;
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                System.out.println(email);
                System.out.println("if user.getEmail()= " + user.getEmail());
                exuser = user;
                return exuser;
                // break;

            } else {

                System.out.println("else user.getEmail()= " + user.getEmail());
                // validuser = false;
                System.out.println("else part ");
                continue;
            }
        }
        return null;
    }
}
