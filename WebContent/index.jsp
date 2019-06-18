<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>CRS</title>
<link rel="stylesheet" type="text/css" href="css/index.css" >
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notify.js" type="text/javascript"></script>

<%if(request.getAttribute("error")!=null){out.print("<script>$.notify.defaults({ className: \"success\" });$.notify(\"Access granted\",{globalPosition:\'top center\'}\"));<\\script>");}%>

</head>
<body>
<div class="clearfix">

<jsp:include page="Template/header.jsp"></jsp:include>
<jsp:include page="Template/adv_search.jsp"></jsp:include>
<jsp:include page="Template/content.jsp"></jsp:include>
<jsp:include page="Template/footer.jsp"></jsp:include>
</div>
</body>
</html>
