<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.Admin" %>

<%
  Admin user = (Admin)request.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<script>
$(document).ready(function(){
		  $("#profile_content").show();
		  $("#inbox_content").hide();
		  $("#users_content").hide();
		  $("#companies_content").hide();
		  $("#profile_content").css("visibility","visible");
		  $("#profile").addClass("highlight1");  
		  $("#companies").removeClass("highlight1");
		  $("#users").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#profile_content").css({height:$("#p_wrapper").height()});
		  });
</script>

</head>
<body>
<jsp:include page="../head.jsp"></jsp:include>
<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="users_content" class="rt" ><div id="us_wrapper" ></div></div>
<div id="companies_content" class="rt" ><div id="cs_wrapper" ></div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" >
<form action="Admin_Controller">
<table>
<tr>
<th>First Name</th>
<td><input type="text" name="firstname" value=<%=user.getAdmin_first_name()%>  /></td>
</tr>
<tr>
<th>Last Name</th>
<td><input type="text" name="lastname" value=<%=user.getAdmin_last_name() %> /></td>
</tr>
<tr>
<th>Email</th>
<td><input type="text" name="email" value=<%=user.getAdmin_email()%> /></td>
</tr>
<tr>
<th>Mobile No</th>
<td><input type="text" name="mobileno" value=<%=user.getAdmin_mobileno()%> /></td>
</tr>
<tr>
<th><tr>
<th>Gender</th>
<td><input type="radio" name="gender" value="male" <%if(user.getAdmin_gender().equalsIgnoreCase("male"))out.print("checked=\"checked\""); %> /> Male <br> 
	 	<input type="radio" name="gender" value="female" <%if(user.getAdmin_gender().equalsIgnoreCase("female"))out.print("checked=\"checked\""); %> /> Female </td>
</tr>
<tr>
<th>Password</th>
<td><input type="text" name="password" value=<%=user.getAdmin_password()%> /></td>
</tr>
<tr>
<td colspan="2" align="Center"><input type="submit" name="profile_update" value="Update" /></td>
</tr>

</table>
</form>
</div></div>
</div>
</div>
</div>

<jsp:include page="../../Template/footer.jsp"></jsp:include>

</body>
</html>