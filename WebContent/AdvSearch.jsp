<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.bean.Job" %>
  <%@ page import="java.util.List" %>
  <%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" type="text/css" href="css/job.css" >
<link rel="stylesheet" type="text/css" href="css/index.css" >

</head>
<body>
<jsp:include page="Template/header.jsp"></jsp:include>
<jsp:include page="Template/adv_search.jsp"></jsp:include>
<div id="content">
<%
	List<Job> l = (List<Job>)request.getAttribute("jobs");
Iterator<Job> it = l.listIterator();
int i=0,d=l.size();
%>


<table id="job">
<%
	while(it.hasNext() && i<d ){Job post = l.get(i);i++;
%>
<tr  >
<td style="border:solid 1px black;" >
<pre><b><a href="Job_Search?jobid=<%=post.getJob_id() %>" ><%=post.getJob_title() %></a></b> , <%=post.getPosted_on() %></pre>
<pre><%=post.getCompnay_name() %><input type="hidden" name="job_id" value="<%=post.getJob_id() %>" /></pre>
<span><%=post.getLocation() %> , <%=post.getExp_min() %> - <%=post.getExp_max() %> (years) : <%=post.getJob_description() %></span>
</td>
</tr>
<%} %>
</table>

</div>
<jsp:include page="Template/footer.jsp"></jsp:include>
</body>
</html>