package com.controller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.aspose.words.Document;
import com.bean.DBConnector;
import com.bean.Job;
import com.bean.Resume;
import com.bean.User;
import com.dao.Job_Search;
import com.dao.Job_Search_Methods;
import com.dao.User_DB;
import com.dao.User_DB_Methods;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import org.pdfbox.pdfparser.PDFParser;

/**
 * Servlet implementation class Resume_Upload
 */
@WebServlet("/Resume_Upload")
public class Resume_Upload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 400 * 1024;
	private File file ;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resume_Upload() {
        
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		if(ServletFileUpload.isMultipartContent(request)){
//      try {
//      	System.out.println("hi");
//          List<FileItem> multiparts = new ServletFileUpload(
//                                   new DiskFileItemFactory()).parseRequest(request);
//        
//          for(FileItem item : multiparts){
//              if(!item.isFormField()){
//                  String name = new File(item.getName()).getName();
//                  item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
//              }
//          }
//     
//         //File uploaded successfully
//         request.setAttribute("message", "File Uploaded Successfully");
//      } catch (Exception ex) {
//         request.setAttribute("message", "File Upload Failed due to " + ex);
//      }          
//   
//  }
	// Directory where files will be saved
		
//		// TODO Auto-generated method stub
//		  File seshdir = new File(UPLOAD_DIRECTORY);
//
//		  if (!seshdir.exists()) {
//		   seshdir.mkdirs();
//		  }
//
//		  FileItemFactory factory = new DiskFileItemFactory();
//
//		  // Create a new file upload handler
//		  ServletFileUpload upload = new ServletFileUpload(factory);
//
//		  // Parse the request
//		  List items = null;
//		  try{
//		   items = upload.parseRequest(request);
//		   System.out.println(items);
//		  } catch (FileUploadException e) {
//			  e.printStackTrace();
//		   throw new ServletException("Exception while uploading the file");
//		  }
//
//		  for (Iterator it = items.iterator(); it.hasNext();) {
//			  DiskFileItem diskFileItem = (DiskFileItem) it.next();
//			  if (diskFileItem.isFormField()) {
//			  continue;
//			  }
//			  byte[] fileBytes = diskFileItem.get();
//			  File file = new File(seshdir, diskFileItem.getName());
//			  FileOutputStream fileOutputStream = new FileOutputStream(file);
//			  fileOutputStream.write(fileBytes);
//			  fileOutputStream.flush();
//			  fileOutputStream.close();
//			  }
//
//		
//	}
	      // Check that we have a file upload request
		
		HttpSession s = request.getSession();
		User user = (User)s.getAttribute("user");
		long userid = user.getUserid();
		
		Job_Search_Methods jsm = new Job_Search();
		int k = jsm.count_resumes(userid);
		if(k<5)
		{
		String base = getServletContext().getRealPath("/");
		filePath = base+"/tempfiles/";
		File theDir = new File(filePath);
		System.out.println(base+" "+theDir);
		if (!theDir.exists()) theDir.mkdir();
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("<p>No file uploaded</p>"); 
	         return;
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();

	      String fname;
	      FileItem fi=null;
	      String resume_name="";
	      while ( i.hasNext () ) 
	      {
	    	  fi = (FileItem)i.next();
	    	  if(fi.isFormField ()){
	    		  String name = fi.getFieldName();//text1
	    	      resume_name = fi.getString();
 
	    	  }
	    	  else{
	    	  	
	    	  	if ( !fi.isFormField () )	
	    	  	{
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            String fileName = fi.getName();
	            String contentType = fi.getContentType();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( fileName.substring( fileName.lastIndexOf("\\"))) ;
	            	}
	            else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            	}
	            fi.write( file ) ;
	            request.setAttribute("message", "File Uploaded Successfully");
	            }
	    	  }
	      }
	      
	      
	       fname   = fi.getName();
	         System.out.println(Files.probeContentType(file.toPath()));
	 		if(Files.probeContentType(file.toPath()).equals("application/pdf")){
	 			File file2 = new File(theDir.getAbsolutePath()+"\\"+userid+"_"+fname+".pdf");
	 			file.renameTo(file2);
	 		}
	 		else{
	         
	         Document doc = new Document(theDir.getAbsolutePath()+"\\"+ fname);
	         // Save the document in PDF format.
	         doc.save( theDir.getAbsolutePath()+"\\"+userid+"_"+fname+".pdf");

	 
	 			}
	 		
	 		
	 		
	         fname = fname+".pdf";
	    
	         User_DB_Methods udm = new User_DB();
	         udm.insert_resume(fname, userid,resume_name);
	      }
	     
	   catch(Exception ex) {
	       ex.printStackTrace();
	   	}
		
	      RequestDispatcher rd = request.getRequestDispatcher("user/user.jsp");
	      rd.forward(request, response);
	}
	
	else{
		
		 RequestDispatcher rd = request.getRequestDispatcher("user/user.jsp");
	      rd.forward(request, response);
		}
		}
	
}
