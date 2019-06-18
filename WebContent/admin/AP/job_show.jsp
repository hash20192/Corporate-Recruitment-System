<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Job" %>
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
		 
	 $("#companies_content").show();
	  $("#inbox_content").hide();
	  $("#users_content").hide();
	  $("#profile_content").hide();
	  $("#companies_content").css("visibility","visible");
	  $("#companies").addClass("highlight1");
	  $("#profile").removeClass("highlight1");
	  $("#users").removeClass("highlight1");
	  $("#inbox").removeClass("highlight1"); 
	  $("#companys_content").css("color","black");
	  $("#cs_wrapper").css({"height":"relative","color":"black","padding-bottom":"20px"});
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
<div id="companies_content" class="rt" ><div id="cs_wrapper" ></div></div>
<h3 align="center">List Of Jobs</h3>
<%
	List<Job> l = (List<Job>)request.getAttribute("jobs");
Iterator<Job> it = l.listIterator();
int i=0,d=l.size();
%>


<table >
<%
	while(it.hasNext() && i<d ){Job post = l.get(i);i++;
%>
<tr  >
<td><%=post.getJob_id() %></td>
<td style="border:solid 1px black;" ><a href="Company_Controller?job_edit=<%=post.getJob_id() %>" ><%=post.getJob_title() %></a>
</td>
<td><form action="${pageContext.request.contextPath}/Admin_Controller"><input type="submit" name="status_job"  <%if(post.isJob_status()==true)out.println("value='Deactivate'");else{out.println("value='Activate'");} %> /><input type="hidden" name="id" value=<%=post.getJob_id()+","+post.isJob_status()+","+post.getCompany_id()%>  /></form>
</td>
<td>
<%=post.getPosted_on() %>
</td>
</tr>
<%} %>
</table>
</div>
</div>

</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>