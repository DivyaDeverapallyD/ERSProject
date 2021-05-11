package com.ers.servlets;

import java.io.IOException;

import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.Dao.UserDao;
import com.ers.Model.Reimbursement;
import com.ers.Model.User;
import com.ers.Service.ReimbursementService;
import com.ers.Service.UserService;
import com.ers.Util.AES;
import com.ers.Util.AtbashCipher;
import com.ers.Util.CryptoUtils;
import com.ers.Util.Mail1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * Servlet implementation class LoadLoginServlet
 */
@WebServlet("/login")
public class LoadLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UserService userService = new UserService();
	  UserDao userDao = new UserDao();
	static ReimbursementService  reimServ = new ReimbursementService();

	// verifyLoginCredentials
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("In LoadLoginServlet Get Method");
		//request.getRequestDispatcher("HTML/welcome.html").forward(request, response);
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		PrintWriter pw = response.getWriter();
		response.setContentType("appllication/json");
	      response.setCharacterEncoding("UTF-8");
	      String name="";
	      if(u !=null)
	    	   name= u.getUserFirstName() + " "+ u.getUserLastName();
		System.out.println(name);
		System.out.println(u.getUserLastName());
		pw.write("Hello"+ " "+name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In LoadLoginServlet POST Method");

		ObjectMapper mapper = new ObjectMapper();

		User u = mapper.readValue(request.getInputStream(), User.class);
		System.out.println(u);
		String pwd=CryptoUtils.encrypt(u.getUserPwd());
		/* boolean val= userDao.validateUser(u.getUserName(),u.getUserPwd()); */
		boolean val= userDao.validateUser(u.getUserName(),pwd);
		//String encPwd= AES.encrypt(u.getUserPwd(), "ers");
		//System.out.println("Encrpted "+encPwd);
		System.out.println(val);
		if(val)
		{
			System.out.println("Boolean value "+ val);
			//User user1 = userService.findUser(u.getUserName(), u.getUserPwd());
			User user1 = userService.findUser(u.getUserName(),pwd);

			HttpSession session = request.getSession();
			session.setAttribute("user", user1);

			System.out.println("got USerDAO");
			PrintWriter pw = response.getWriter();
			response.setHeader("Cache_Control","no-cache,no-store,must-revalidate");
			response.setContentType("appllication/json");
		      response.setCharacterEncoding("UTF-8");
		      if(user1.getUserRoleId()==1)
		      {
		    	 try {
					Mail1.sendEmail(user1.getUserEmail());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	pw.write("employee");
		    	
		      }
		      else if(user1.getUserRoleId()== 2)
		      {
		    	  try {
						Mail1.sendEmail(user1.getUserEmail());
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	 pw.write("manager"); 
		      }
		      // pw.write("mona");
		       //pw.close();
			//response.sendRedirect("HTML/welcome.html");
		     //  response.sendError(0, getServletInfo());
		
		}
		
		
	//	System.out.println(u);
		 else {
			
			PrintWriter pw = response.getWriter();
		//	response.setCharacterEncoding(getServletInfo());
			pw.write("invalid");

		}

	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ObjectMapper mapper = new ObjectMapper();
	
		Reimbursement r = mapper.readValue(request.getInputStream(), Reimbursement.class);
		System.out.println("Divya Inside doPut"+ r);
		System.out.println("In login doput reimb id  "+r.getReimb_id());
		System.out.println("In login doput  reimb status id  "+r.getReimb_status_id());
		ReimbursementService.updateStatus(r.getReimb_id(), r.getReimb_status_id());
		PrintWriter pw = response.getWriter();
		response.setContentType("appllication/json");
		response.setHeader("Cache_Control","no-cache,no-store,must-revalidate");
	      response.setCharacterEncoding("UTF-8");
	      pw.write("reimb updated succsessgully");

		
	}

}
