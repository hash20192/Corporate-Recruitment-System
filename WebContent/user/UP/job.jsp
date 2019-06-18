<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bean.Job" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%Job job = new Job();job = (Job)request.getAttribute("job"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JOB</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/job.css" />
<style>
#resume_selection{
margin-top:50px;
background-color: white;
font-family: sans-serif;
width: 600px;
margin-left: auto;
margin-right: auto;
padding: 20px;
}

#resume_selection table{
margin:10px;
}

#resume_selection input[type="button"]{
margin-top:10px;
padding:5px;
width: 90px;
border: none;
}
</style>


<script type="text/javascript" language="javascript">

$( document ).ready(function() {

	$('.simple-ajax-popup-align-top').magnificPopup({
		type: 'ajax',
		alignTop: true,
		overflowY: 'scroll', // as we know that popup content is tall we set scroll overflow by default to avoid jump
		
	});
	


function get_resume_list()
{
    //Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
    xmlhttp.open("GET","User_Controller?get_resumes=get_resumes", true);
     
    //When readyState is 4 then get the server output
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) { 
            if (xmlhttp.status == 200) 
            {
            	
            } 
            else
            {
                alert('Something is wrong !!');
            }
        }
    };
     
    xmlhttp.send(null);
}


 
 
});

</script>
<script type="text/javascript" language="javascript">
function myFunction() {

    //Creating a new XMLHttpRequest object
    var xmlhttp;
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
    }
    //Create a asynchronous GET request
  var resume_id = $("input:radio[name ='group1']:checked").val();  
   
     var company_id = <%=job.getCompany_id()%>;
    var job_id = <%=job.getJob_id()%>;
    var job_title = "<%=job.getJob_title()%>";
    var company_name = "<%=job.getCompnay_name()%>";
    var job_id = <%=job.getJob_id()%>;
 
 
    xmlhttp.open("GET","Job_Search?job_id="+job_id+"&company_id="+company_id+"&job_title="+job_title+"&company_name="+company_name+"&resume_id="+resume_id+"&job_apply=job_apply", true);
     
    //When readyState is 4 then get the server output
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) { 
            if (xmlhttp.status == 200) 
            {
            	$.magnificPopup.close();
            	notif({  msg: "Job <b>Applied</b>",
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
}   
</script>
</head>
<body>
<jsp:include page="../head.jsp"></jsp:include>
<div id="content">
<div id="notify"></div>
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
<label><a class="simple-ajax-popup-align-top" href="User_Controller?get_resumes=get_resumes" >Apply Now</a></label>
</div>
<fieldset>
<legend>Job Description</legend>
<span><%=job.getJob_description()%></span>
</fieldset>
</div>
</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
</body>
</html>