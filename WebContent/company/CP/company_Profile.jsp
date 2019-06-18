<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.Company" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>
<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>


<% Company company = (Company) request.getAttribute("company");  %>
<script>
$(document).ready(function(){
		  $("#profile_content").show();
		  $("#inbox_content").hide();
		  $("#cj_content").hide();
		  $("#sj_content").hide();
		  $("#profile_content").css("visibility","visible");
		  $("#profile").addClass("highlight1");  
		  $("#show_jobs").removeClass("highlight1");
		  $("#create_job").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");
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
<div id="cj_content" class="rt" ><div id="cj_wrapper" ></div></div>
<div id="sj_content" class="rt" ><div id="sj_wrapper" ></div></div>
<div id="profile_content" class="rt1"><div id="p_wrapper" >
<form action="Company_Controller" >
<fieldset class="profile1" >
<legend>HR Details</legend>
<label>First Name</label><input type="text" name="emr_firstname" value="<%=company.getEmr_firstname() %>" /><br />
<label>Last Name</label><input type="text" name="emr_lastname" value="<%=company.getEmr_lastname() %>" /><br />
<label>Mobile No</label><input type="text" name="emr_mobileno" value="<%=company.getEmr_mobileno() %>" /><br />
<label>Gender</label>
Male<input type="radio" name="emr_gender" value="male" <%if(company.getEmr_gender().equalsIgnoreCase("male"))out.print("checked='checked'"); %>  />
Female<input type="radio" name="emr_gender" value="female" <%if(company.getEmr_gender().equalsIgnoreCase("female"))out.print("checked='checked'"); %> />
<br />
<label>Nationality</label>
	<select name="emp_nationality" >
	<option <%if(company.getEmr_nationality().equalsIgnoreCase("indian"))out.print("selected='selected'"); %> >Indian</option>
	<option <%if(company.getEmr_nationality().equalsIgnoreCase("american"))out.print("selected='selected'"); %>>American</option>
	<option <%if(company.getEmr_nationality().equalsIgnoreCase("african"))out.print("selected='selected'"); %>>African</option>
	</select>
<br />
<br />
<label>Email</label><input type="text" name="emr_email" value="<%=company.getEmr_email()%>" />
<br />
<label>Address</label>
	<textarea name="emr_address" > <%=company.getEmr_address() %>
	</textarea>
<br />
</fieldset>
<fieldset class="profile1" >
<legend>Company Details</legend>
<label>Company ID</label><input value="<%=company.getCompany_id() %>" name="company_id" readonly="readonly" /><br />
<label>Name</label><input name="company_name" value="<%=company.getCompany_name() %>" /><br />
<label>Email</label><input name="company_email" value="<%=company.getCompany_email() %>" /><br />
<label>Address</label><textarea name="company_address" ><%=company.getCompany_address() %></textarea><br />
</fieldset>

<input type="submit" name="company_profile" value="Update" />

</form>
</div></div>
</div>
</div>

</div>

<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>