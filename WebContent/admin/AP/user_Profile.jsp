<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.bean.User"%>


<%
  User user = (User)request.getAttribute("user");
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
		  
		  $("#users_content").show();
		  $("#inbox_content").hide();
		  $("#companies_content").hide(); 
		  $("#profile_content").hide();
		  $("#users_content").css("visibility","visible");
		  $("#users").addClass("highlight1");
		  $("#companies").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#users_content").css({"color":"black",height:$("#us_wrapper").height()});
		  });      
	

 
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
<div id="users_content" class="rt" ><div id="us_wrapper" >
<form  action="${pageContext.request.contextPath}/User_Controller" >
<fieldset class="profile">
<legend>Profile</legend>
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
</fieldset>
</form>
</div>
</div>
</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>