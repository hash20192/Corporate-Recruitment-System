<%@ page import="com.bean.Admin" %>
<%HttpSession s =request.getSession();Admin admin = (Admin)s.getAttribute("user"); %>
<form id="test-form" class="message-box mfp-hide" action="${pageContext.request.contextPath}/Admin_Controller">
	
	<fieldset style="border:0;">
	<h1>Message </h1><hr/>
	
		<ul>
			<li>
				<div id="to">
				<label for="To">To :</label>
				<select id="select_user" required>
				<option value="" >Select User</option>
				<option value="user">User</option>
				<option value="company">Company</option>
				</select>
				<select name="message_option" required>
				<option value="">Select Message Type</option>
				<option value="internal" >Internal</option>
				<option value="external" >External</option>
				<option value="both" >Both</option>
				</select>
				</div>
			</li>
			<li>
				<label for="From">From :</label>
				<input id="from" name="from" type="email" value=<%=admin.getAdmin_email()%> placeholder="example@domain.com" required readonly >
			</li>
			<li>
				<label for="Message">Subject :</label>
				<input  id="message_title" name="message_title" placeholder="Message Title" required ></input>
			</li>
			<li>
				<label for="Message">Message :</label>
				<textarea rows="10" cols="45" id="message" name="message" placeholder="Write message Here" required ></textarea>
			</li>
			<li>
			<button type="submit" name="message_send" value="Send" onclick="send_message()"  >Send</button>
			</li>
		</ul>
	
	</fieldset>
	
</form>