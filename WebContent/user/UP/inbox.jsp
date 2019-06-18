<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.bean.User"%>
     <%@ page import="com.bean.Messages" %>
 <%@ page import="java.util.List" %>
  <%@page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%HttpSession s = request.getSession();
  User user = (User)s.getAttribute("user");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome <%=user.getUserid() %></title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	
		  $("#inbox_content").show();
		  $("#resume_content").hide();
		  $("#profile_content").hide();
		  $("#inbox_content").css("visibility","visible");
		  $("#inbox").addClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#resume").removeClass("highlight1");
	  
});

</script>


</head>
<%List l = (List)request.getAttribute("messages"); 
Iterator it = l.listIterator();
int i=0,d=l.size();
%>
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
<div id="inbox_content" class="rt1">
<div class="wrap_messages">
<table id="con_messages" cellpadding="3px" >
<%
	while(it.hasNext() && i<d ){Messages m = (Messages)l.get(i);i++;
%>
<tr>
<td>
<input type="checkbox" >
</td>
<th>
<a href="${pageContext.request.contextPath}/User_Controller?message_show=job_apply_show&thread_id=<%=m.getThread()%>" ><%=m.getTitle() %></a>
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
<div id="popup"></div>
</body>
</html>