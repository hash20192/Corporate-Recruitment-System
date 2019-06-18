package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

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
import com.bean.Resume;
import com.bean.User;
import com.dao.User_DB;
import com.dao.User_DB_Methods;

/**
 * Servlet implementation class Resume_Update
 */
@WebServlet("/Resume_Update")
public class Resume_Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 400 * 1024;
	private File file ;
	private String resume_title;
	private String job_ids;
	private long resume_id_update;
	private String fname;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resume_Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		
			HttpSession s = request.getSession();
			User user = (User)s.getAttribute("user");
			long userid = user.getUserid();
			User_DB_Methods udb = new User_DB();
			String base = getServletContext().getRealPath("/");
			filePath = base+file.separator+"tempfiles"+file.separator;
		
			 try{ 
			
			 isMultipart = ServletFileUpload.isMultipartContent(request);
		      response.setContentType("text/html");
		      java.io.PrintWriter out = response.getWriter( );
		      if( !isMultipart ){
		       System.out.println("No Multipart Req");
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

		     
		      // Parse the request to get file items.
		      List fileItems = upload.parseRequest(request);
			
		      // Process the uploaded file items
		      Iterator i = fileItems.iterator();
		      while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if( fi.isFormField () ){
		        	 if(fi.getFieldName().equals("resume_title")){
		        		 resume_title = fi.getString();
		        	 }
		        	 else if(fi.getFieldName().equals("job_ids")){
		        		 job_ids = fi.getString();
		        	 }
		        	 else if(fi.getFieldName().equals("resume_id_update")){
		        		 resume_id_update = Long.parseLong(fi.getString());
		        	 }
		         }
		         else	
		         {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               file = new File( filePath + 
		               fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		               file = new File( filePath + 
		               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            fname   = fi.getName();
		         }
		      }
		      
		      File theDir = new File(filePath);
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
		      
		      	Resume r = udb.get_resume_details(resume_id_update);
				long resume_id = resume_id_update;
				String resume = r.getResume();
				File f =new File(filePath+userid+"_"+resume);
				boolean b =f.delete();
				if(b){
					System.out.println("file deleted");
				}
				User_DB_Methods udm = new User_DB();
				udm.update_resume_details(resume_id,fname,resume_title,job_ids,userid);
		   
		   }catch(Exception ex) {
		       ex.printStackTrace();
		   }
		      RequestDispatcher rd = request.getRequestDispatcher("user/user.jsp");
		      rd.forward(request, response);
		}
		
	

}
