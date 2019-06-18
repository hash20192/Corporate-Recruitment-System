<div id="user_login_menu" >
<ul>
<li><a href="${pageContext.request.contextPath}/user/user.jsp" >Home</a></li>
<li><a href="#" >Contect Us</a></li>
</ul>
</div>
<div id="header" class="header glass">


<div id="logo">
<a href="user.jsp"><img alt="CRS" src="${pageContext.request.contextPath}/images/crs_logo.png" />
</a>
</div>

<div id="user_login_center">

<div id="user_login_search">
<form action="${pageContext.request.contextPath}/Job_Search">
<input type="text" name="quary" placeholder="Search" />
<input type="submit" name="search" value="Search" />
<input type="reset" name="reset" value="Reset" />
</form>
</div>
</div>

<div id="mandn">
<a id="c_mail" href="#test-form" class="popup-with-form" ><img alt="" src="${pageContext.request.contextPath}/images/mail.png" height="32px" width="32px"></a>
<a id="notifications" href="${pageContext.request.contextPath}/User_Controller?get_notification=get_notification" class="simple-ajax-popup" ><img alt="" src="${pageContext.request.contextPath}/images/notify.png" height="32px" width="32px"></a>
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
 </div>
</div>
<jsp:include page="UP/create_message.jsp"></jsp:include>
<jsp:include page="userjs.jsp"></jsp:include>