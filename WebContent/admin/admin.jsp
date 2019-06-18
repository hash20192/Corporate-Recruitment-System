<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.bean.Admin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>

<jsp:include page="../Template/allcss.jsp"></jsp:include>
<jsp:include page="../Template/alljs.jsp"></jsp:include>

<script>
$(document).ready(function(){
	
	$("#inbox").click(function(){
		  $("#inbox_content").show();
		  $("#users_content").hide();
		  $("#companies_content").hide();
		  $("#profile_content").hide();
		  $("#inbox_content").css("visibility","visible");
		  $("#inbox").addClass("highlight1");
		  $("#users").removeClass("highlight1");
		  $("#companies").removeClass("highlight1");
		  $("#profile").removeClass("highlight1");
	  });
	

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
<jsp:include page="AP/create_message.jsp"></jsp:include>
<jsp:include page="head.jsp"></jsp:include>
<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="users_content" class="rt" ><div id="us_wrapper" ></div></div>
<div id="companies_content" class="rt" ><div id="cs_wrapper" ></div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" ></div></div>
</div>
</div>
</div>
<jsp:include page="../Template/footer.jsp"></jsp:include>
</body>
</html>