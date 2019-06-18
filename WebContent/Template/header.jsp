<%@ page import="com.bean.Error"  %>

<div id="header">

<div id="logo">
<a href="index.jsp"><img alt="CRS" src="images/crs_logo.png"></a>
</div>

<ul id="menu">
<li><a href="${pageContext.request.contextPath}/index.jsp" >Home</a></li>
<li><a href="#" >Contect Us</a></li>
</ul>

<div id="meta">
<a id="hire" href="${pageContext.request.contextPath}/company/CP/company_login.jsp"><label>Looking To Hire ?</label></a> |
<a id="hire" href="${pageContext.request.contextPath}/user/UP/user_login.jsp"><label>Register</label></a>
<br />
<br />

<form action="${pageContext.request.contextPath}/User_Start" id="metaform" >
<table id="index_login">
<tr>
<td>
Email
</td>
<td>
Password
</td>
<td>
</td>
</tr>
<tr>
<td>
<input id="email" type="text" name="useremail" placeholder="email" class="userlogin" />
</td>
<td>
<input type="password" name="userpass" placeholder="password" class="userlogin" /> 
</td>
<td>
<input type="submit" name="user"  class="usersubmit" value="Sign In" >
</td>
</table>
<input type="hidden" name="type" value="1" >
</form>
</div>
</div>
