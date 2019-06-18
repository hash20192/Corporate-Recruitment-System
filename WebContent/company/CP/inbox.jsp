<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.JobApply" %>
 <%@ page import="java.util.List" %>
  <%@page import="java.util.Iterator"%>
    <%@ page import="com.bean.Messages" %>
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
<%List l = (List)request.getAttribute("job_apply"); 
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
<div class="wrap_messages">
<%if(request.getAttribute("null")!=null){String nof=(String)request.getAttribute("null");out.print(nof);} %>
<table id="con_messages" cellpadding="3px" >
<%
	while(it.hasNext() && i<d ){JobApply ja = (JobApply)l.get(i);i++;
%>
<tr>
<td>
<input type="checkbox" >
</td>
<th>
<a href="${pageContext.request.contextPath}/Company_Controller?job_apply_show=job_apply_show&job_id=<%=ja.getJob_id()%>" ><%=ja.getJob_title() %></a>
</th>
<th>
<%=ja.getCount() %>
</th>
<td>
, <%=ja.getDate() %>
</td>
</tr>
<%} %>
</table>
<%List<Messages> l1 = (List<Messages>)request.getAttribute("messages"); 
Iterator<Messages> it1 = l1.listIterator();
int j=0,e=l1.size();
%>
<table id="con_messages" cellpadding="3px">
<%
	while(it1.hasNext() && j<e ){Messages m = (Messages)l1.get(j);j++;System.out.print(m.getThread());
%>
<tr>
<td>
<input type="checkbox" >
</td>
<th>
<a href="${pageContext.request.contextPath}/Company_Controller?message_show=job_apply_show&thread_id=<%=m.getThread()%>" ><%=m.getTitle() %></a>
</th>
<td>
, <%=m.getDate() %>
</td>
</tr>
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