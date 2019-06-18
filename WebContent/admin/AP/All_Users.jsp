<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%List<User> l = (List<User>)request.getAttribute("allUsers"); Iterator i = l.iterator();int d=0,c=l.size();int j=0;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>


<script>
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
<script>
function user_profile(clicked_name,clicked_value)
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
    document.getElementById("us_wrapper").innerHTML=xmlhttp.responseText;
    $("#users_content").css({"color":"black",height:"auto"});
    }
  }
xmlhttp.open("GET","admin_Controller?"+clicked_name+"="+clicked_value,true);
xmlhttp.send();
}
</script>

<script>
function company_profile(clicked_name,clicked_value)
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
    document.getElementById("cs_wrapper").innerHTML=xmlhttp.responseText;
    $("#companies_content").css({"color":"black",height:"auto"});
    }
  }
xmlhttp.open("GET","admin_Controller?"+clicked_name+"="+clicked_value,true);
xmlhttp.send();
}
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
<div id="users_content" class="rt" ><div id="us_wrapper" >
<table width="500px" >
<tr>
<th>User ID</th><th>User Email</th><th>User Status</th><th>Activate/Deactivate</th><th>Profile</th>
</tr>
<%while(i.hasNext() && d<c) {%>
<%User user = (User)l.get(d);d++; %>
<tr>
<td><%=user.getUserid() %><input type="hidden" id="<%=j%>" name="<%=j%>" value="<%=user.getUserid() %>" ></td>
<td><%=user.getEmail() %></td>
<td><%=user.getStatus() %></td>
<td><form action="${pageContext.request.contextPath}/Admin_Controller"><input type="submit" name="status_user"  <%if(user.getStatus()==true)out.println("value='Deactivate'");else{out.println("value='Activate'");} %> /><input type="hidden" name="id" value=<%=user.getUserid()+","+user.getStatus()%>  /></form></td>
<td><a href="${pageContext.request.contextPath}/Admin_Controller?user_profile=<%=user.getUserid()%>">Profile</a></td>
</tr>
<%j++;%>
<%} %>
</table>
</div>
</div>
</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>