package com.ers.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.Dao.ReimbursementDao;
import com.ers.Model.Reimbursement;
import com.ers.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class FilterByStatusServlet
 */

@WebServlet("/byStatus")
public class FilterByStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ReimbursementDao rDao = new ReimbursementDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterByStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("in bystatus servlet");
		//ObjectMapper mapper = new ObjectMapper();
		/*
		 * System.out.println("quesry st"+request.getQueryString()); System.out.println(
		 * "url"+ request.getRequestURL());
		 */
;

		//Reimbursement r = mapper.readValue(request.getInputStream(), Reimbursement.class);
		String  id= request.getParameter("statusId");
		System.out.println("Sttsus id "+ id);
	int statusId=Integer.parseInt(id);
		ObjectMapper mapper = new ObjectMapper();
		//  System.out.println("In get By status servlet "+ r);
		  
		  List<Reimbursement> reim = rDao.findByStatus(statusId);
		  System.out.println("respnse for find by status");
		  for(Reimbursement r : reim)
		  {
			  System.out.println(r);
		  }
		  //ObjectMapper mapper = new ObjectMapper(); 
		  String json = mapper.writeValueAsString(reim);
		  
		  System.out.println("manager servlet" + json); 
		  PrintWriter writer = response.getWriter(); 
		  response.setContentType("application/json");
		  response.setHeader("Cache_Control","no-cache,no-store,must-revalidate");
		  writer.write(json);;
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
