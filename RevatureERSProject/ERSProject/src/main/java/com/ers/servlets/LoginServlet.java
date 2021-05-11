package com.ers.servlets;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.Dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		System.out.println(username);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		System.out.println("hai");
	String username = request.getParameter("username");
     // String password = request.getParameter("password");
      System.out.println(username);
     // System.out.println(password);
        String password= "Divya@17";
	//	String username = "DivyaSandeep";
	  //     String password = "Divya@17" ;
        String destPage="HTML/login.html";
        UserDao userDao = new UserDao();
        try {
        	boolean userValidation= userDao.validateUser(username, password);
        	if(userValidation)
        	{
        		destPage="HTML/welcome.html";
        	}
        	else
        	{
        		String message = "Invalid Credentials";
        		request.setAttribute("message", message);
        	}
        	
        	RequestDispatcher dispatcher= request.getRequestDispatcher(destPage);
        	dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        
        
	}

}
