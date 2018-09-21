package com.kg.emailclient.controller;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import com.kg.emailclient.model.Inbox;

import org.omg.CORBA.Request;
@WebServlet("/InboxController")
public class InboxController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    ArrayList<Inbox> inboxListuser1 = new ArrayList<Inbox>();
    // Inbox inbox1 = new Inbox(1, "date1", "from1", "to1", "subject1", "message1");
    // Inbox inbox11 = new Inbox(1, "date11", "from11", "to11", "subject11", "message11");
    // Inbox inbox2 = new Inbox(2, "date2", "from2", "to2", "subject2", "message2");
    // inboxListuser1.add(inbox1);
    // inboxListuser1.add(inbox11);
    // inboxListuser1.add(inbox2);
   
    private String mode = "";
    
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
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
            } catch(ServletException |IOException e)
            {
    
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
            // int userid = Integer.parseInt(req.getParameter("userid"));
            // String firstName = req.getParameter("firstName");
            // User newUser = new User(userid, firstName);
            // userList.add(newUser);
    
            req.setAttribute("inbox1", inboxListuser1);
            RequestDispatcher view = req.getRequestDispatcher("inbox.jsp");
            view.forward(req, resp);
        }
    
        private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("showAddForm called");

            RequestDispatcher view = req.getRequestDispatcher("inputi.jsp");
            view.forward(req, resp);
    
        }
    
        private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("showEditForm called");
            mode = "update";
            int userid = Integer.parseInt(req.getParameter("userid"));
            System.out.println(userid);
    
          Inbox updateInbox = new Inbox();
    
            for (Inbox inbox : inboxListuser1) {
                if (inbox.getUserid() == userid) {
                    updateInbox =inbox ;
                    break;
                }
    
            }
            req.setAttribute("inbox1", updateInbox);
            RequestDispatcher view = req.getRequestDispatcher("inputi.jsp");
            view.forward(req, resp);
        }

        
        private void saveorupdateUser(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            System.out.println("saveorupdateUser called");
    
            if (mode != "update") {
                System.out.println("entering the save or update method if statement");
                int userid = Integer.parseInt(req.getParameter("userid"));
                String date = req.getParameter("date");
                String from = req.getParameter("from");
                String to = req.getParameter("to");
                String subject  = req.getParameter("subject");
                String message = req.getParameter("message");
                Inbox  newuser = new Inbox(userid,date,from,to,subject,message);
                
               
                    inboxListuser1.add(newuser);
    
                
                    req.setAttribute("inbox1", inboxListuser1);
                    RequestDispatcher view = req.getRequestDispatcher("inbox.jsp");
                    view.forward(req, resp);
            
                }

     
            


        
    
                
    
                
             else {
                System.out.println("edit user called arraylist to be updated here");
                System.out.println("entering the save or update method else statement ");
    
                int userid = Integer.parseInt(req.getParameter("userid"));
                System.out.println(userid);
                
                String date = req.getParameter("date");
                String from = req.getParameter("from");
                String to = req.getParameter("to");
                String subject  = req.getParameter("subject");
                String message = req.getParameter("message");
                Inbox  updateUser = new Inbox(userid,date,from,to,subject,message);
    
                for (Inbox inbox : inboxListuser1) {
                    if (inbox.getUserid() == userid) {
                        inboxListuser1.set(inboxListuser1.indexOf(inbox), updateUser);
                        mode="";

                        break;
                    }
    
                }
             req.setAttribute("inbox1",inboxListuser1);   
            RequestDispatcher view = req.getRequestDispatcher("inbox.jsp");
            view.forward(req, resp);
    
            }

    
    
        }
    
        private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int userid = Integer.parseInt(req.getParameter("userid"));
            for (Inbox inbox : inboxListuser1) {
                if (inbox.getUserid() == userid) {
    
                    System.out.println(inbox);
                    inboxListuser1.remove(inboxListuser1.indexOf(inbox));
                    break;
                }
    
            }
    
            req.setAttribute("inbox1", inboxListuser1);
            RequestDispatcher view = req.getRequestDispatcher("inbox.jsp");
            view.forward(req, resp);
        }
    
    }
    
    
    
        



