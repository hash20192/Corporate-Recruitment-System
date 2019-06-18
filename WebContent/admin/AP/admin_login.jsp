

<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="txet/css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frostedglass.css" >
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div id="content">

<form action="${pageContext.request.contextPath}/Admin_Start">
<fieldset class="login header glass" id="admin_login" >
<legend>Sign In</legend>
<p>
<label>Email ID </label>
<input name="adminemail" type="text" >
</p>
<p>
<label>PASSWORD </label>
<input name="adminpass" type="password" >
</p>
<p>
<button type="submit" name="user" value="Login" >Login</button>
</p>
<input type="hidden" name="type" value="0" >
</fieldset>
</form>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>





