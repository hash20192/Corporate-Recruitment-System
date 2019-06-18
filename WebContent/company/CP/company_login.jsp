<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="../../css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frostedglass.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rhinoslider-1.05.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/mousewheel.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
<link href="../../css/rhinoslider-1.05.css" rel="stylesheet" type="text/css" />

</head>
<script type="text/javascript">
			$(document).ready(function(){
				$('#slider').rhinoslider({
					controlsPlayPause: false,
                    showControls: 'always',
                    showBullets: 'always',
		    controlsMousewheel: false,
		    prevText: 'Back',
                    slidePrevDirection: 'toRight',
		    slideNextDirection: 'toLeft'
				});
				
			});
				
		</script>

<body>

<jsp:include page="../header.jsp"></jsp:include>
<div id="content">
<div id="user_login_left" >
<jsp:include page="company_registration.jsp"></jsp:include>
</div>
<div id="user_login_right">
<form action="${pageContext.request.contextPath}/Company_Start">
<fieldset class="login header glass" id="company_login">
<legend>Sign In</legend>
<p>
<label>COMPANY Email</label>
<input name="companyemail" type="text" >
</p>
<p>
<label>PASSWORD</label><input name="companypass" type="password" >
</p>
<p id="login_buttons">
<Button type="submit" name="user" value="Login" >Login</Button>
</p>
<input type="hidden" name="type" value="2" />

</fieldset>
</form>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>