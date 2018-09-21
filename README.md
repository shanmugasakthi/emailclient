package com.kg.usermgmt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactController")
public class ContactController extends HttpServlet {

    ArrayList<Contact> userList1 = new ArrayList<Contact>();
    private String mode = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
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
        userList1.add(new Contact(1, "aravinth12321@gmail.com","984857562"));
        userList1.add(new Contact(2, "kumar12321@gmail.com","982365487"));
        userList1.add(new Contact(3, "guru12321@gmail.com","987564223"));

        req.setAttribute("userList", userList1);
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

        Contact updateUser = new Contact();

        for (Contact user : userList1) {
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
            String phno = req.getParameter("phone");
            
            
            // for (Contact user : userList) {
                // if(user.getUserid()!=userid)
                // {
                Contact newUser = new Contact(userid,email,phno);
                userList1.add(newUser);
               req.setAttribute("userList", userList1);
                // }
                
            // }
                  // req.setAttribute("userList", userList);
        } else {
            System.out.println("edit user called arraylist to be updated here");

            int userid = Integer.parseInt(req.getParameter("userid"));
            System.out.println(userid);
            String email= req.getParameter("email");
            String phno= req.getParameter("phone");
            
            Contact updateUser = new Contact(userid,email,phno);

            for (Contact user : userList1) {
                if (user.getUserid() == userid) {
                    userList1.set(userList1.indexOf(user), updateUser);
                    break;
                }

            }

        }

        req.setAttribute("userList", userList1);
        RequestDispatcher view = req.getRequestDispatcher("userlist.jsp");
        view.forward(req, resp);

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userid = Integer.parseInt(req.getParameter("userid"));
            for (Contact user : userList1) {
            if (user.getUserid() == userid) {

                System.out.println(user);
                userList1.remove(userList1.indexOf(user));
                break;
            }

        }

        req.setAttribute("userList", userList1);
        RequestDispatcher view = req.getRequestDispatcher("userlist.jsp");
        view.forward(req, resp);
    }

}
