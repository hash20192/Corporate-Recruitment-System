<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/notifIt.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function(){		
							 $('.popup-with-form').magnificPopup({
									type: 'inline',
									preloader: false,
									focus: '#name',

									// When elemened is focused, some mobile browsers in some cases zoom in
									// It looks not nice, so we disable it:
									callbacks: {
										beforeOpen: function() {
											if($(window).width() < 700) {
												this.st.focus = false;
											} else {
												this.st.focus = '#name';
											}
										}
									}
								});
							  
							  $("#select_user").change(function () {
								  if($('#to').find("input[name^='user']").length >0){
									 
							        	 $("#add").remove();
							         
								  }
							         if($(this).val()=="admin"){
							        	  $("#to").append("<div id='add' ><input class='field' id='"+$(this).val()+"' name='user"+$(this).val()+"' type='email' value='" + $(this).val() + "' placeholder='example@domain.com' readonly required /></div>");      	
							 }
							         else{
							        	  $("#to").append("<div id='add' ><input class='field' id='"+$(this).val()+"' name='user"+$(this).val()+"' type='email' value='" + $(this).val() + "' placeholder='example@domain.com' required /></div>");

							         }
							     });
							  
						
							  
});						
</script>

<script>
function DropDown(el) {
	this.dd = el;
	this.initEvents();
}
DropDown.prototype = {
	initEvents : function() {
		var obj = this;
		
		obj.dd.on('click', function(event){
			$(this).toggleClass('active');
			event.stopPropagation();
		});	
	}
}
$(function() {
	var dd = new DropDown( $('#dd') );
	$(document).click(function() {
		// all dropdowns
		$('.wrapper-dropdown-2').removeClass('active');
	});

});

function send_message(){
	

	$("#test-form").submit(function(e)
			{
				e.preventDefault();
				 
				$.magnificPopup.close();
			    var postData = $(this).serialize();
			    var formURL = $(this).attr("action");
			 
			 
			   
			    if (window.XMLHttpRequest){
			        xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
			    } else {
			        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
			    }
			    //Create a asynchronous GET request
			    xmlhttp.open("GET", formURL+"?"+postData+"&message_send=send", true);
			      	 
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
  };
xmlhttp.open("GET","Company_Controller?job_edit=job_edit&job_id="+id,true);
xmlhttp.send();
}

</script>