<%@page import="java.util.List" %>
<%@ page import="com.bean.Error"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frostedglass.css" >
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div id="content" >
<div id="user_login_left">
<jsp:include page="user_registration.jsp"></jsp:include>
</div>
<div id="user_login_right">
<div>

<fieldset class="login header glass" id="user_login" >

<%Error e=null;if(request.getAttribute("error")!=null){e = (Error)request.getAttribute("error") ; } %>
<legend>Sign In</legend>
<form action="${pageContext.request.contextPath}/User_Start" >
<span><%if(e!=null && e.getLfail()!=null){out.println(e.getLfail());}%></span>

 <p>
<label>Email ID</label><input name="useremail" type="text" placeholder="Email" />
</p>

<span><%if(e!=null && e.getEmail()!=null){out.println(e.getEmail());}%></span>
 <p>
<label>PASSWORD</label><input name="userpass" type="password" placeholder="Password" />
</p>

<span><%if(e!=null && e.getPassword()!=null){out.print(e.getPassword());}%></span>
<p>
<Button type="submit" name="user" value="Login" >Login</Button>    
</p>
<input type="hidden" name="type" value="1" > 
</form>
</fieldset>


</div>
</div>
</div>

<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>