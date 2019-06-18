<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company</title>
<jsp:include page="../Template/allcss.jsp"></jsp:include>
<jsp:include page="../Template/alljs.jsp"></jsp:include>

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
<div id="popup"></div>

<jsp:include page="head.jsp"></jsp:include>

<div id="content">
<div class="row">
<div class="left" class="highlight" >
<jsp:include page="left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt"><center>INBOX</center></div>
<div id="cj_content" class="rt" ><div id="cj_wrapper" ></div></div>
<div id="sj_content" class="rt" ><div id="sj_wrapper" ></div></div>
<div id="profile_content" class="rt"><div id="p_wrapper" ></div></div>
</div>
</div>
<!-- <form action="company_Controller">
<button type="submit" name="job_create" >Create Job</button>
</form>
<form action="company_Controller">
<button type="submit" name="job_show" >Show Jobs</button>
</form> -->
</div>

<jsp:include page="../Template/footer.jsp"></jsp:include>
</body>
</html>