<div id="header">

<div id="logo">
<a href="${pageContext.request.contextPath}/company/company.jsp"><img alt="CRS" src="${pageContext.request.contextPath}/images/crs_logo.png" />
</a>
</div>

	<div id="menu">
	<ul>
	<li><a href="${pageContext.request.contextPath}/company/company.jsp" >Home</a></li>
	<li><a href="#" >Contect Us</a></li>
	</ul>
	</div>

	<div id="mandn">
			<a id="c_mail" href="#test-form" class="popup-with-form" ><img alt="" src="${pageContext.request.contextPath}/images/mail.png" height="32px" width="32px"></a>
			<a id="notifications" href="#test-form" class="popup-with-form" ><img alt="" src="${pageContext.request.contextPath}/images/notify.png" height="32px" width="32px"></a>
		</div>
	
	
	
	<div  id="drop_menu">
		<div id="dd" class="wrapper-dropdown-2" tabindex="1">menu<span><img src="${pageContext.request.contextPath}/images/menu.png"/></span>
							<ul class="dropdown" style="padding-left:0px;margin-top:0px;margin-bottom:0px">
							
									<li><a href="#">your settings <span class="icon"> </span></a></li>
									<li><a href="#">user stats<span class="icon stat"> </span></a></li>
									<li><a href="#">direct message<span class="icon msg"> </span></a></li>
									<li><a href="${pageContext.request.contextPath}/Logout?Logout=Logout">sign out<span class="icon signout"> </span></a></li>
							</ul>
		</div>
	<!-- <a href="user_Controller?profile=Profile" ><button type="submit" name="profile" value="profile">Profile</button></a> | <a href="Logout?Logout=Logout" ><button type="submit" name="Logout">Logout</button></a>
 -->
	</div>

		
	
</div>
<jsp:include page="CP/create_message.jsp"></jsp:include>