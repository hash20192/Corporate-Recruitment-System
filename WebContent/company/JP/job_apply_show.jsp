<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Resume" %>
 <%@ page import="java.util.List" %>
  <%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>

<script>
$(document).ready(function(){
	
		  $("#inbox_content").show();
		  $("#cj_content").hide();
		  $("#sj_content").hide();
		  $("#profile_content").hide();
		  $("#inbox_content").css("visibility","visible");
		  $("#inbox").addClass("highlight1");
		  $("#show_jobs").removeClass("highlight1");
		  $("#create_job").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");

  
		  
});
</script>

</head>
<body>
<%List l = (List)request.getAttribute("user_list"); 
Iterator it = l.listIterator();
int i=0,d=l.size();
%>
<jsp:include page="../head.jsp"></jsp:include>

<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt">
<table>
<tr>
<td>
user Id
</td>
<td>
User Email
</td>
<th>
Resume
</th>
<th>
Posted On
</th>
</tr>
<%
	while(it.hasNext() && i<d ){Resume r = (Resume)l.get(i);i++;
%>
<tr>

<td>
<%=r.getUser_id() %>
</td>
<th>
<%=r.getUser_email() %>
</th>
<th>
<a href="${pageContext.request.contextPath}/Company_Controller?view_resume=view_resume&resume_name=<%=r.getResume()%>&user_id=<%=r.getUser_id()%>" target="_blank" >Resume View</a> |
<a href="${pageContext.request.contextPath}/Company_Controller?fileName=<%=r.getResume()%>">Resume Download</a>
</th>
<td>
<%=r.getPost_on() %>
</td>
</tr>
<%} %>
</table>
</div>
</div>
</div>
</div>

<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>