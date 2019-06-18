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
		 
		  $("#sj_content").show();
		  $("#inbox_content").hide();
		  $("#cj_content").hide();
		  $("#profile_content").hide();
		  $("#sj_content").css("visibility","visible");
		  $("#show_jobs").addClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#create_job").removeClass("highlight1");
		  $("#inbox").removeClass("highlight1");  
		  $("#sj_content").css("color","black");
		  $("#sj_wrapper").css({"height":"relative","color":"black","padding-bottom":"20px"});
		  });
</script>

<script>
function loadJob(id)
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
    document.getElementById("sj_wrapper1").innerHTML=xmlhttp.responseText;
    $("#sj_wrapper").css({"visibility":"hidden"});
    }
  }
xmlhttp.open("GET","Company_Controller?job_edit=job_edit&job_id="+id,true);
xmlhttp.send();
}
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
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="cj_content" class="rt" ><div id="cj_wrapper" ></div></div>
<div id="sj_content" class="rt" ><div id="sj_wrapper" >
<h3 align="center">List Of Jobs</h3>
<%
	List<Job> l = (List<Job>)request.getAttribute("jobs");
Iterator<Job> it = l.listIterator();
int i=0,d=l.size();
%>


<table  class="jobs"  >
<%
	while(it.hasNext() && i<d ){Job post = l.get(i);i++;
%>
<tr  >
<td style="border:solid 1px black;" ><pre><b><a href="Company_Controller?job_edit=<%=post.getJob_id() %>" ><%=post.getJob_title() %></a></b>, <%=post.getPosted_on() %></pre>
<pre><%=post.getCompnay_name() %><input type="hidden" name="job_id" value="<%=post.getJob_id() %>" /></pre>
<span><%=post.getLocation() %>, <%=post.getExp_min()%> - <%=post.getExp_max() %>(years) : <%=post.getJob_description() %></span>
</td>
</tr>
<%} %>
</table>
</div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" ></div></div>
</div>
</div>


</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>