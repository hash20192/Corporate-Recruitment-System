<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.awt.*" %>
<%@ page import="javax.imageio.*" %>
<%@ page import="com.bean.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%HttpSession s = request.getSession();
  User user = (User)s.getAttribute("user");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome <%=user.getUserid() %></title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	
		  $("#resume_content").show();
		  $("#inbox_content").hide();
		  $("#profile_content").hide();
		  $("#resume_content").css("visibility","visible");
		  $("#resume").addClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");
          $("#resume_content").css({
            	    "min-height":$("#resume_options").height()
          });
          $("#resume_content").css({"margin-bottom":"20px","color":"black"});
          });



</script>

</head>
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
<div id="resume_content" class="rt"><div id="resume_options">

<form action="User_Controller" >
<fieldset class="resume">
<legend>Basic Information</legend>
<p>
<label for="full_name" >Full Name</label>
<input type="text" name="full_name" id="full_name" />
<label for="email" >Email</label>
<input type="text" name="email" id="email" />
</p>
<p>
<label>Phone Numbers</label>
<input type="text" name="phone_numbers" />
<label>Websites</label>
<input type="text" name="websites" />
</p>
<p>
<label>Address Line 1</label>
<input type="text" name="address_line1" />
<label>Address Line 2</label>
<input type="text" name="address_line2" />
</p>
<p>
<label>Address Line 3</label>
<input type="text" name="address_line3" />
</p>
</fieldset>
<fieldset  class="resume" >
<legend >Work Experience</legend>
<p>
<label>Job Title</label>
<input type="text" name="job_title" />
<label>Company Name</label>
<input type="text" name="company_name" />
</p>

<p>
<label>Start Date</label>
<input type="text" name="start_date" />
<label>End Date</label>
<input type="text" name="end_date" />
</p>
<p>
<label>Other Information</label>
<textarea rows="8" cols="30" name="other_information" ></textarea>
</p>
</fieldset>

<fieldset class="resume" >
<legend>Qualification</legend>
<p>
<label>Qualification</label>
<textarea rows="8" cols="30" name="qualification" ></textarea>
</p>
</fieldset>
<fieldset class="resume" >
<legend>Education</legend>
<p>
<label>Course Name</label>
<input type="text" name="q_course_name" />
<label>Institution Name</label>
<input type="text" name="q_institution_name" />
</p>
<p>
<label>Start Date</label>
<input type="text" name="q_start_date" />
<label>End Date</label>
<input type="text" name="q_end_date" />
</p>
<p>
<label>Other Information</label>
<textarea rows="8" cols="30" name="q_other_information" ></textarea>
</p>
</fieldset>
<fieldset class="resume">
<legend>Interests</legend>
<p>
<label>Interests</label>
<textarea rows="8" cols="30" name="interests" ></textarea>
</p>
</fieldset>
<fieldset class="resume" >
<legend>References</legend>
<p>
<label>References</label>
<input type="text" name="references" />
</p>
</fieldset>
<p id="resume_p">
<button type="submit" name="resume_Add" id="resume_add" class="resume_button" >ADD RESUME</button><button type="submit" name="resume_show" id="resume_show" class="resume_button" >Show Resume</button><button type="submit" name="resume_create" id="resume_create" class="resume_button" >Generate PDF</button>
</p>
</form>
</div></div>
<div id="profile_content" class="rt"><div id="profile_wrapper"></div></div>


</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
<div id="popup"></div>
</body>
</html>