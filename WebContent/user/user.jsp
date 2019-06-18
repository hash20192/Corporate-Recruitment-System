<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%HttpSession s = request.getSession();
  User user = (User)s.getAttribute("user");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome <%=user.getUserid() %></title>


<jsp:include page="../Template/allcss.jsp"></jsp:include>
<jsp:include page="../Template/alljs.jsp"></jsp:include>


<script type="text/javascript">
$(document).ready(function(){
	$("#inbox").click(function(){
		  $("#inbox_content").show();
		  $("#resume_content").hide();
		  $("#profile_content").hide();
		  $("#inbox_content").css("visibility","visible");
		  $("#inbox").addClass("highlight1");
		  $("#profile").removeClass("highlight1");
		  $("#resume").removeClass("highlight1");
	  });
	
	

		$('.simple-ajax-popup-align-top').magnificPopup({
			type: 'ajax',
			alignTop: true,
			overflowY: 'scroll' // as we know that popup content is tall we set scroll overflow by default to avoid jump
		});

		$('.simple-ajax-popup').magnificPopup({
			type: 'ajax'
		});
		
	});

	


</script>



</head>
<body>

<div id="head">
<jsp:include page="head.jsp"></jsp:include>
</div>
<div id="content" class="layout">
<div class="row">

<div class="left header glass" >
<jsp:include page="left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="inbox_content" class="rt1"><center>INBOX</center>

</div>
<div id="resume_content" class="rt"><div id="resume_options"></div></div>
<div id="profile_content" class="rt"><div id="profile_wrapper"></div></div>
</div>
</div>
</div>
<jsp:include page="../Template/footer.jsp"></jsp:include>
<div id="popup"></div>
</body>
</html>