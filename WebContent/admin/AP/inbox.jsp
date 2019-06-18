<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Messages" %>
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

<script>
function deletemessages()
{
	var val = 0;
	var String=''; 
	      
	        
	        $('.delete_m:checked').each(function(){
	        	if(val==0){
	        		String =$(this).val() ;
	        	}
	        	else{
	        		String = String+","+$(this).val();
	        	}
	          
	          
	          
	        });
	     
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
	  notif({  msg: "Messages <b>Deleted</b>",
  		  type: "success",
  		  width: 500,
  		  height: 60,
  		position: "center",
  		});
    }
  }
xmlhttp.open("GET","Admin_Controller?message_delete=message_delete&thread_id="+String,true);
xmlhttp.send();
}
</script>

</head>
<body>
<%List l = (List)request.getAttribute("messages"); 
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
<div><button name="delete" value="delete" onclick="deletemessages()" >Delete</button></div>
<table>
<tr>
<td>
</td>
<td>
ID
</td>
<th>
Title
</th>
<th>
Date
</th>
</tr>
<%
	while(it.hasNext() && i<d ){Messages m = (Messages)l.get(i);i++;
%>
<tr>
<td>
<input type="checkbox" id="ad_Checkbox<%=i%>" name="selector[]" class="delete_m" value=<%=m.getThread() %> >
</td>
<td>
<%=m.getId() %>
</td>
<th>
<a href="${pageContext.request.contextPath}/Admin_Controller?message_show=job_apply_show&thread_id=<%=m.getThread()%>" ><%=m.getTitle() %></a>
</th>
<th>
<%=m.getDate() %>
</th>
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