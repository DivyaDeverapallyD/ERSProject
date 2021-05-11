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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ReimbursementDao rDao = new ReimbursementDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Reimbursement> reim = rDao.findAll();
		
		for(Reimbursement r : reim)
		{
			System.out.println("Divyaaaa"+r.getAuthor());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reim);
		
System.out.println("manager servlet" + json);
		PrintWriter writer = response.getWriter();
		response.setHeader("Cache_Control","no-cache,no-store,must-revalidate");
		response.setContentType("application/json");
		writer.write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
