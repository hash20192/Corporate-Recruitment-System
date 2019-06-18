<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.bean.User"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.Resume" %>

<%
  User user = (User)request.getAttribute("user");
  List l = (List)request.getAttribute("resume");
  Iterator it = l.listIterator();
  int i=0,d=l.size();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome <%=user.getUserid() %></title>
<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	
	
		  $("#profile_content").show();
		  $("#inbox_content").hide();
		  $("#resume_content").hide();
		  $("#profile_content").css("visibility","visible");
		  $("#profile").addClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#resume").removeClass("highlight1");
          $("#profile_content").css({"height":$(".left").height()});
              $("#profile_content").css({
          	    "padding-bottom":"30px"
          	});
              
           
});
 
</script>
<script type="text/javascript">
function check_resume(click_id){
	
	    //Creating a new XMLHttpRequest object
	    var xmlhttp;
	    var id =click_id;
	    if (window.XMLHttpRequest){
	        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	    } else {
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
	    }
	    
	    //Create a asynchronous GET request
	    xmlhttp.open("GET","User_Controller?delete_resume=delete_resume&resume_id="+id, true);
	     
	    //When readyState is 4 then get the server output
	    xmlhttp.onreadystatechange = function() {
	        if (xmlhttp.readyState == 4) { 
	            if (xmlhttp.status == 200) 
	            {
	            	document.getElementById("list_jobs").innerHTML=xmlhttp.responseText;
	            	var str = $("#job_list").val();
	            	if(str!=0){
	            	var str1 = $("#resume_id_update").val();
	            	window.location.reload();
	            	 notif({  msg: "your Resume is applied for  <b>"+str+" "+str1+"</b>",
   		      		  type: "success",
   		      		  width: 500,
   		      		  height: 60,
   		      		position: "center",
   		      		});
	            	
	            	 $.magnificPopup.open({
	            		  items: {
	            		    src: $("#resume-delete-pro"),
	            		    	type: 'inline'
	            		  },
	            		  type: 'inline',
	            		 
	            		});
	            	}
	            	else{
	            		 notif({  msg: "your Resume is <b> Deleted</b>",
	      		      		  type: "success",
	      		      		  width: 500,
	      		      		  height: 60,
	      		      		position: "center",
	      		      		});
	            	}
	            }
	            else
	            {
	                alert('Something is wrong !!');
	            }
	        }
	    };
	     
	    xmlhttp.send(null);
	
}
</script>

</head>
<body>
<div id="head">
<jsp:include page="../head.jsp"></jsp:include>

</div>
<div id="content" class="layout">
<div class="row">
<div class="left" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="resume_content" class="rt"><div id="resume_options"></div></div>
<div id="profile_content" class="rt"><div id="profile_wrapper">

<fieldset class="profile">
<legend>Profile</legend>
<form  action="${pageContext.request.contextPath}/User_Controller" >
<p>
<label>First Name</label> 
<input type="text" name="firstname" value=<%=user.getFirstname()%>  />
</p>
<p>
<label>Last Name</label> 
<input type="text" name="lastname" value=<%=user.getLastname() %> />
</p>
<p>
<label>Email</label> 
<input type="text" name="email" value=<%=user.getEmail()%> />
</p>
<p>
<label>Mobile No</label>
<input type="text" name="mobileno" value=<%=user.getMobileno()%> />
</p>

<p>
<input type="submit" name="profile_update" value="Update" />
</p>
</form>
</fieldset>
<fieldset class="profile">
<legend>Resume</legend>
<%while(it.hasNext() && i<d ){Resume r = (Resume)l.get(i);i++;%> 
<p>
<label>Resume <%=i %>:</label> <label><%=r.getResume_title() %></label><button id="<%=r.getResume_id() %>" name="del<%=i %>" class="delete_resumes" value="Delete" onclick="check_resume(this.id)" >Delete</button><br />
</p>
<%} %>
<form method="post" action="${pageContext.request.contextPath}/Resume_Upload" enctype="multipart/form-data">
<%if(i<5){out.print("<p><label>Resume :</label><br/><br/><label>Resume Title :</label><input type='text' name='reusme_title' /><br/><input type='file' name='file' /><br/><input type='submit' value='Upload File' /></p>");} %>

</form>

</fieldset>


<div id="resume-delete-pro" class="message-box mfp-hide " >
	<form  id="resume_delete_update" method="post" action="${pageContext.request.contextPath}/Resume_Update" enctype="multipart/form-data" >
	<fieldset style="border:0;">
	<h1>Please Upload New Resume</h1><hr/>
	
		<ul>
			
			<li>
				<h1>Resume :</h1><br/><br/>
				<label>Resume Title :</label><input type='text' id="resume_title_update" name='resume_title' /><br/>
				<input type='file' id="file_update" name='file' /><br/>
				<div id="list_jobs">
				</div>
			</li>
			
			
		</ul>
			<input type="submit" id="update_resume" name="update_resume" value="Update"  />
	
	</fieldset>
	
</form>
</div>
</div></div>

</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
<div id="popup"></div>
</body>
</html>