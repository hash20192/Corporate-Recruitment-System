<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.bean.Company" %>

<%HttpSession s = request.getSession();Company company = (Company)s.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>
<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<script>
$(document).ready(function(){
		  
			  
		  $("#cj_content").show();
		  $("#inbox_content").hide();
		  $("#sj_content").hide(); 
		  $("#profile_content").hide();
		  $("#cj_content").css("visibility","visible");
		  $("#create_job").addClass("highlight1");
		  $("#show_jobs").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#cj_content").css({"color":"black",height:$("#cj_wrapper").height()});
		  });      

</script>

</head>
<body>
<div id="popup"></div>
<jsp:include page="../head.jsp"></jsp:include>

<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="cj_content" class="rt" ><div id="cj_wrapper" >
<form action="${pageContext.request.contextPath}/Company_Controller">
<table id="job_post" >
<tr>
<th>Company ID</th>
<td><%=company.getCompany_id()%></td>
</tr>
<tr>
<th>Company Name</th>
<td><%=company.getCompany_name() %></td>
</tr>
<tr>
<th>Job Title</th>
<td><input name="job_title" type="text" /></td>
</tr>
<tr>
<th>Nationality</th>
<td><select name="nationality" id="nationality"><option>India</option>
								<option>UK</option>
								<option>Other</option>
	</select></td>
</tr>
<tr>
<th>location</th>
<td><select name="location" id="location" ><option>Gandhinagar</option>
							<option>Surat</option>
							<option>Other</option>
	</select></td>
</tr>
<tr>
<th>Experience</th>
<td></td>
</tr>
<tr>
<th></th>
<td><label>Min</label><select name="min_experience" id="mine"><%for(int i=0;i<10;i++){ %><option><%out.println(i);%></option><%} %><option>>10</option></select><label>To</label>
<label>Max</label><select name="max_experience" id="maxe"><%for(int i=0;i<30;i++){ %> <option><%out.println(i); %></option><%} %><option>>30</option></select></td>
</tr>
<tr>
<th>Keyword \ Skills</th>
<td><input type="text" name="skills" /></td>
</tr>
<tr>
<th>Function</th>
<td><input type="text" name="function" /></td>
</tr>
<tr>
<th>Role</th>
<td><input type="text" name="role" /></td>
</tr>
<tr>
<th>Industry</th>
<td><input type="text" name="industry" /></td>
</tr>
<tr>
<th>Job Description</th>
<td><textarea wrap="off" rows="5" cols="8" name="job_description" id="jod" ></textarea></td>
</tr>
<tr>
<td></td>
<td><input type="button" name="jd" value="View" id="jd" onclick="al()" ></td>
</tr>
<tr>
<th>About Company</th>
<td><textarea wrap="off" rows="5" cols="8" name="about_company"></textarea></td>
</tr>
<tr>
<td colspan="2" align="center">
<button type="submit" name="job_post" value="Post Job" >Post Job</button>
</td>
</tr>
</table>
</form>
</div></div>
<div id="sj_content" class="rt" ><div id="sj_wrapper" ></div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" ></div></div>
</div>
</div>
<!-- <form action="company_Controller">
<button type="submit" name="job_create" >Create Job</button>
</form>
<form action="company_Controller">
<button type="submit" name="job_show" >Show Jobs</button>
</form> -->


</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>
