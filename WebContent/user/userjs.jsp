<script type="text/javascript" >
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
</script>