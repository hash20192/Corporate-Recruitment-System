
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Messages" %>
  <%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>

	<%List<Messages> l = (List<Messages>)request.getAttribute("messages");
	Iterator<Messages> it = l.listIterator();Messages m=null; %>
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
function send_reply()
{
	$("#reply_from").submit(function(e)
			{
				e.preventDefault();
				$.magnificPopup.close();
			     postData = $(this).serialize();
			     formURL = $(this).attr("action");
			     alert(postData+" "+formURL);
			    if (window.XMLHttpRequest){
			        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
			    } else {
			        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
			    }
			    //Create a asynchronous GET request
			    xmlhttp.open("GET", formURL+"?"+postData, true);
			      	 
			      	  //When readyState is 4 then get the server output
			          xmlhttp.onreadystatechange = function() {
			              if (xmlhttp.readyState == 4) { 
			                  if (xmlhttp.status == 200) 
			                  {
			                	  notif({  msg: "Message <b>Sent</b>",
			    		      		  type: "success",
			    		      		  width: 500,
			    		      		  height: 60,
			    		      		position: "center",
			    		      		});
			                  } 
			                  else
			                  {
			                      alert('Something is wrong !!');
			                  }
			              }
			          };
			           
			          xmlhttp.send(null);
			 });
	}
</script>
<script> 
$(document).ready(function(){
  $("#slide").click(function(){
    $("#panel").slideToggle("slow");
  });
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
<div id="inbox_content" class="rt">

<%
int i=0,d=l.size();
while(it.hasNext() && i<d ){m = l.get(i);i++;
%>
<div id="message_body">
<table>
<tr>
<th><label>From :</label></th>
<td><span><%=m.getFrom() %></span></td>
</tr>
<tr>
<th><label>To :</label></th>
<td><span><%=m.getTo() %></span></td>
</tr>
<tr>
<th><label>Subject :</label></th>
<td><span><%=m.getTitle() %></span></td>
</tr>
<tr>
<th><label>Message :</label></th>
<td></td>
</tr>
<tr><th></th>
<td><span><%=m.getMessage() %></span></td>
</tr>
</table>
</div>
<%} %>
<div id="reply">
<label id="slide" >Reply</label><br>
<br>

<div id="panel">
<form id="reply_form" action="${pageContext.request.contextPath}/Admin_Controller">
<textarea name="reply" rows="10" cols="60"></textarea><br>
<input type="hidden" name="thread_id" value=<%=m.getThread() %> >
<input type="hidden" name="to" value=<%=m.getTo() %> >
<input type="hidden" name="from" value=<%=m.getFrom() %> >
<input type="hidden" name="title" value="<%=m.getTitle() %>" >
<input type="submit" name="reply_send" value="Reply" onclick="send_reply()"/>
</form>
</div>
</div>
</div>
</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>