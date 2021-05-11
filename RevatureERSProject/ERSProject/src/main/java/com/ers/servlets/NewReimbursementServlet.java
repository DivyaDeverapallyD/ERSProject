package com.ers.servlets;

import java.io.File;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.ers.Dao.ReimbursementDao;
import com.ers.Model.Reimbursement;
import com.ers.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class NewReimbursements
 */
@WebServlet("/newReim")
public class NewReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "images";
	
	static ReimbursementDao rDao = new ReimbursementDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		System.out.println("in newReim get method");
		//load reimbursements into json file
		List<Reimbursement> reim = rDao.findReims(u.getUserId());
		for(Reimbursement r : reim)
		{
			System.out.println(r);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reim);
		
		//log.trace("All Reimbursements: " + json);

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
		System.out.println("New post method");
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement r = mapper.readValue(request.getInputStream(), Reimbursement.class);
	//	System.out.println("Divya"+r.getReimb_rec().split("\\"));
		System.out.println("nnnnnnnnnnnnnnnnnnnnn");
		System.out.println(r.getReimb_rec());
		//r.getReimb_rec().replace("\\", "/");
		
		//String[] arr= r.getReimb_rec().split("//");
		//System.out.println(arr[0]);
		//System.out.println("Divyaaaa"+arr[arr.length-1]);
		
		/*
		 * File source = new File("E:/Divya/JS.docx"); File dest = new
		 * File("E/Divya/Revature/Foodar.docx");
		 * 
		 * 
		 * copyFileUsingApacheCommonsIO(source,dest);
		 */
		
		
		File source = new File("E:/Divya/Images/"+r.getReimb_rec());
		
		File dest = new File("E:/Divya/Revature/Projects/ERSProject/ersproject/ERS/src/main/webapp/images/"+r.getReimb_rec());
		copyFileUsingApacheCommonsIO(source,dest);
	//	r.setReimb_rec(arr[arr.length-1]);
	//	getImagePath(request);
	//	System.out.println("Divya Full Image Path "+ imagePath);
	//	r.setReimb_rec(imagePath);
		System.out.println("divya deb image path"+ r.getReimb_rec());
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println("in newReim post method");
		r.setReimb_author(u.getUserId());
		System.out.println(r);
		r = rDao.save(r);
		PrintWriter out = response.getWriter();
		response.setHeader("Cache_Control","no-cache,no-store,must-revalidate");
		response.setContentType("application/json");
		//response.sendRedirect("http://localhost:8081/ERS/HTML/pastTickets.html");
		out.write("saved");
		
	}
	
	
	
	
	public void getImagePath(HttpServletRequest request) throws IOException, ServletException
	{ try {
        String description = request.getParameter("description");
        System.out.println("Description: " + description);
        //E:\Divya\Revature\Projects\ERSProject\ersproject\Project1\src\main\webapp\images
         
        // Gets absolute path to root directory of web app.//E:\Divya\Revature\Projects\fileupload\fileupload\src\main\webapp
        String appPath = "E:\\Divya\\Revature\\Projects\\ERSProject\\ersproject\\Project1\\src\\main\\webapp";
        System.out.println("finding app path"+appPath);
        System.out.println(appPath);
        appPath = appPath.replace('\\', '/');

        // The directory to save uploaded file
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
            System.out.println(fullSavePath);
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
            System.err.println(fullSavePath);
        }

        // Creates the save directory if it does not exists
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

         
         System.out.println(request.getParts());
        // Part list (multi files).
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (fileName != null && fileName.length() > 0) {
                String filePath = fullSavePath + File.separator + fileName;
                System.out.println("Write attachment to file: " + filePath);
                // Write to file
                part.write(filePath);
            }
        }
        // Upload successfully!.
     //   response.sendRedirect("uploadFileResults.jsp");
    } catch (Exception e) {
        e.printStackTrace();
       // request.setAttribute("errorMessage", "Error: " + e.getMessage());
      //  RequestDispatcher dispatcher = request.getRequestDispatcher("uploadFile.jsp");
      //  dispatcher.forward(request, response);
    }
	}
	
	
	 private String extractFileName(Part part) {
	        // form-data; name="file"; filename="C:\file1.zip"
	        // form-data; name="file"; filename="C:\Note\file2.zip"
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                // C:\file1.zip
	                // C:\Note\file2.zip
	                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                clientFileName = clientFileName.replace("\\", "/");
	                int i = clientFileName.lastIndexOf('/');
	                // file1.zip
	                // file2.zip
	                return clientFileName.substring(i + 1);
	            }
	        }
	        return null;
	    }

	 
	 private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
		    FileUtils.copyFile(source, dest);
		    System.out.println("Done");
		}

}
