<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.Job" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%Job job = new Job();job = (Job)request.getAttribute("job"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JOB</title>
<link rel="stylesheet" type="text/css" href="css/job.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" >
<script>
function ajaxAsyncRequest()
{
    //Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
    xmlhttp.open("GET","Job_Search?job_apply=jobapply&job_id=<%=job.getJob_id()%>&job_title=<%=job.getJob_title()%>&company_id=<%=job.getCompany_id()%>&company_name=<%=job.getCompnay_name()%>", true);
     
    //When readyState is 4 then get the server output
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) { 
            if (xmlhttp.status == 200) 
            {
                document.getElementById("notify").innerHTML = xmlhttp.responseText;
            } 
            else
            {
                alert('Something is wrong !!');
            }
        }
    };
     
    xmlhttp.send(null);
}
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div id="content">
<div id="notify"><center></center></div>
<div id="job_show">
<div id="job_title">
<h1>Job</h1>
<h3><%=job.getJob_title() %></h3>
</div>
<div id="wrpper">
<div class="summary" >
<fieldset>
<legend>Job Summary</legend><br />
<label>Company</label><br />
<span><%=job.getCompnay_name() %></span>
<br />
<br />
<label>Locations</label><br />
<span><%=job.getLocation() %></span>
<br />
<br />
<label>Nationality</label><br />
<span><%=job.getNationality() %></span>
<br />
<br />
<label>Experience</label><br />
<span><%=job.getExp_min()%> - <%=job.getExp_max() %></span>
<br />
<br />
<label>Keywords / Skills</label><br />
<span><%=job.getSkills() %></span>
<br />
<br />
<label>Education</label><br />
<span>-</span>
<br />
<br />
<label>Function</label><br />
<span><%=job.getFunction() %></span>
<br />
<br />
<label>Role</label><br />
<span><%=job.getRole() %></span>
<br />
<br />
<label>Industry</label><br />
<span><%=job.getIndustry() %></span>
<br />
<br />
<label>Posted On</label><br />
<span><%=job.getPosted_on() %></span>
<br />
<br />
<label>Job Ref Code</label><br />
<span><%=job.getJob_id() %></span>
</fieldset>
</div>
<div class="description" >
<div id="apply" >
<label id="jobapply" onclick="ajaxAsyncRequest()">Apply Now</label>
</div>
<fieldset>
<legend>Job Description</legend>
<span><%=job.getJob_description() %></span>
</fieldset>
</div>
</div>
</div>
</div>
<jsp:include page="Template/footer.jsp"></jsp:include>
</body>
</html>