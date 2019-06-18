<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Company" %><%@ page import="com.bean.Job" %>
  <%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>

<script>
$(document).ready(function(){
		 
		  $("#sj_content").show();
		  $("#inbox_content").hide();
		  $("#cj_content").hide();
		  $("#profile_content").hide();
		  $("#sj_content").css("visibility","visible");
		  $("#show_jobs").addClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#create_job").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");  
		  $("#sj_content").css("color","black");
		  $("#sj_wrapper").css({"height":"relative","color":"black","padding-bottom":"20px"});
		  });
</script>

<script>
function loadJob(id)
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("sj_wrapper1").innerHTML=xmlhttp.responseText;
    $("#sj_wrapper").css({"visibility":"hidden"});
    }
  }
xmlhttp.open("GET","Company_Controller?job_edit=job_edit&job_id="+id,true);
xmlhttp.send();
}
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
<div id="cj_content" class="rt" ><div id="cj_wrapper" ></div></div>
<div id="sj_content" class="rt" ><div id="sj_wrapper" >
<%
	HttpSession s = request.getSession();Company company = (Company)s.getAttribute("user"); Job post = (Job)request.getAttribute("post");
%>
<form action="company_Controller">
<table >
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
<td><input name="job_title" type="text" value="<%=post.getJob_title() %>" /></td>
</tr>
<tr>
<th>Nationality</th>
<td><select name="nationality" ><option <%if(post.getNationality().equalsIgnoreCase("india"))out.print("selected='selected'"); %> >India</option>
								<option <%if(post.getNationality().equalsIgnoreCase("uk"))out.print("selected='selected'"); %> >UK</option>
								<option <%if(post.getNationality().equalsIgnoreCase("other"))out.print("selected='selected'"); %> >Other</option>
	</select></td>
</tr>
<tr>
<th>location</th>
<td><select name="location"><option <%if(post.getLocation().equalsIgnoreCase("gandhinagar"))out.print("selected='selected'"); %> >Gandhinagar</option>
							<option <%if(post.getLocation().equalsIgnoreCase("surat"))out.print("selected='selected'"); %> >Surat</option>
							<option <%if(post.getLocation().equalsIgnoreCase("other"))out.print("selected='selected'"); %> >Other</option>
	</select></td>
</tr>
<%-- <tr>
<th>Experience</th>
<td> min :<select name="min_experience" ><%for(int i=0;i<10;i++){ %><option <%if(Integer.parseInt(post.getExp_min())==i)out.print("selected='selected'"); %> ><%out.println(i);%></option><%} %><option <%if((post.getExp_min().equals(">10")))out.print("selected='selected'"); %> >>10</option></select> max : <select name="max_experience"><%for(int i=0;i<30;i++){ %> <option <%if(Integer.parseInt(post.getExp_max())==i)out.print("selected='selected'"); %> ><%out.println(i); %></option><%} %><option <%if((post.getExp_max().equals(">30")))out.print("selected='selected'"); %> >>30</option></select></td>
</tr> --%>
<tr>
<th>Keyword \ Skills</th>
<td><input type="text" name="skills" value="<%=post.getSkills() %>" /></td>
</tr>
<tr>
<th>Function</th>
<td><input type="text" name="function" value="<%=post.getFunction() %>" /></td>
</tr>
<tr>
<th>Role</th>
<td><input type="text" name="role" value="<%=post.getRole() %>" /></td>
</tr>
<tr>
<th>Industry</th>
<td><input type="text" name="industry" value="<%=post.getIndustry() %>" /></td>
</tr>
<tr>
<th>Job Description</th>
<td><textarea rows="5" cols="8" name="job_description" ><%=post.getJob_description() %></textarea></td>
</tr>
<tr>
<th>About Company</th>
<td><textarea rows="5" cols="8" name="about_company"><%=post.getAbout_company() %></textarea></td>
</tr>
<tr>
<td colspan="2" align="center">

<button type="submit" name="job_update" value="update Job" >Update</button>
</td>
</tr>
</table>
<input type="hidden" name="job_id" value="<%=post.getJob_id() %>" >
</form>
</div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" ></div></div>
</div>
</div>


</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>