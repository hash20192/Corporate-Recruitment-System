<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.awt.*" %>
<%@ page import="javax.imageio.*" %>
<%@ page import="com.bean.Company" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome</title>

<jsp:include page="../../Template/allcss.jsp"></jsp:include>
<jsp:include page="../../Template/alljs.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	
	  $("#exam_content").show();
	  $("#index_content").hide();
	  $("#cj_content").hide();
	  $("#sj_content").hide();
	  $("#profile_content").hide();
	  $("#inbox_content").css("visibility","visible");
	  $("#create_exam").addClass("highlight1");
	  $("#index").removeClass("highlight1");
	  $("#show_jobs").removeClass("highlight1");
	  $("#create_job").removeClass("highlight1");
	  $("#profile").removeClass("highlight1");


});
</script>
<script>
$(document).ready(function() {

var MaxInputs       = 15; //maximum input boxes allowed
var InputsWrapper   = $("#InputsWrapper"); //Input boxes wrapper ID
var AddButton       = $("#AddMoreFileBox"); //Add button ID

var x = InputsWrapper.length; //initlal text box count
var FieldCount=1; //to keep track of text box added
$(AddButton).click(function (e)  //on add input button click
{
        if(x <= MaxInputs) //max input box allowed
        {
            FieldCount++; //text box added increment
            //add input box
            $(InputsWrapper).append('<div><input type="text" name="que'+FieldCount+'" id="que'+FieldCount +'" placeholder="Enter Question"/><a href="#" class="removeclass">&times;</a><br/><input type="text" name="ch'+FieldCount+'1" placeholder="Choise 1" /><input type="text" name="ch'+FieldCount+'2" placeholder="Choise 2" /><br/><input type="text" name="ch'+FieldCount+'3" placeholder="Choise 3" /><input type="text" name="ch'+FieldCount+'4" placeholder="Choise 4" /></div>');




	    x++; //text box increment
        }
return false;
});

$("body").on("click",".removeclass", function(e){ //user click on remove text
        if( x > 1 ) {
                $(this).parent('div').remove(); //remove text box
                x--; //decrement textbox
		FieldCount--;
        }
return false;
}) 

});
</script>
</head>
<div id="head">
<jsp:include page="../head.jsp"></jsp:include>
</div>
<div id="content" class="layout">
<div class="row">
<div class="left" >
<jsp:include page="../left.jsp"></jsp:include>
</div>
<div class="right" >
<div id="exam_content" class="rt1"><div id="exam_wrapper">

<form action="Company_Controller" >

<div id="InputsWrapper">
<div>
<input type="text" name="que1" placeholder="enter question" /> <br/>
<input type="text" name="ch1"  placeholder="Choise 1" />
<input type="text" name="ch2"  placeholder="Choise 2" /><br/>
<input type="text" name="ch3"  placeholder="Choise 3" />
<input type="text" name="ch4"  placeholder="Choise 4" />
</div>
</div>
<button id="AddMoreFileBox" >Add</button>
</form>
</div></div>



</div>
</div>
</div>
<jsp:include page="../../Template/footer.jsp"></jsp:include>
<div id="popup"></div>
</body>
</html>