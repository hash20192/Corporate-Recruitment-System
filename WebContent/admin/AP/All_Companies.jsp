<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bean.Company"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>

<script>
$(document).ready(function(){
		  $("#companies_content").show();
		  $("#inbox_content").hide();
		  $("#users_content").hide(); 
		  $("#profile_content").hide();
		  $("#companies_content").css("visibility","visible");
		  $("#companies").addClass("highlight1");
		  $("#users").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");
		  $("#companies_content").css({"color":"black",height:$("#cs_wrapper").height()});
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

</head>
<body>
<jsp:include page="../head.jsp"></jsp:include>
<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="companies_content" class="rt" ><div id="cs_wrapper" >
<%List<Company> l = (List<Company>)request.getAttribute("allCompanies"); Iterator i = l.iterator();int d=0,c=l.size();int j=0;%>
<table width="600px" >
<tr>
<th>Company ID</th><th>Company Name</th><th>Company Status</th><th>Activate/Deactivate</th><th>Profile</th><th>All Jobs</th>
</tr>
<%while(i.hasNext() && d<c) {%>
<%Company company = (Company)l.get(d);d++; %>
<tr>
<td><%=company.getCompany_id() %><input type="hidden" id="<%=j%>" name="<%=j%>" value="<%=company.getCompany_id()%>" ></td><td><%=company.getCompany_name() %></td><td><%=company.isCompany_status() %></td><form action="Admin_Controller"><td><input type="submit" name="status_company"  <%if(company.isCompany_status()==true)out.println("value='Deactivate'");else{out.println("value='Activate'");} %> /></td><input type="hidden" name="id" value=<%=company.getCompany_id()+","+company.isCompany_status()%>  /></form>
<td><a href="Admin_Controller?company_profile=<%=company.getCompany_id() %>" >Profile</a></td>
<td><a href="Admin_Controller?all_jobs=<%=company.getCompany_id() %>">All Jobs</a></td>
</tr>
<%j++; %>
<%} %>
</table>
</div></div>
</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>
