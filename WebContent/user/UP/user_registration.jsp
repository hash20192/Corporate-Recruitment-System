<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div>
<fieldset class="head glass">
<legend>Register</legend>
<form action="Register">
<table cellspacing="10px" >
<tr><th>First Name</th>
	<td><input type="text" name="firstname" placeholder="First Name" /></td>
</tr>
<tr><th>Last Name</th>
	<td><input type="text" name="lastname" placeholder="Last Name" /></td>
</tr>
<tr><th>Password</th>
	<td><input type="text" name="password" placeholder="Password"  /></td>
</tr>
<tr><th>Re-Password</th>
	<td><input type="text" name="repassword" placeholder="Re-Enter Password" /></td>
</tr>
<tr><th>Mobile No</th>
	<td><input type="text" name="mobileno" placeholder="Your Mobile No" /> </td>
</tr>
<tr><th>Email</th>
	<td><input type="text" name="email" placeholder="Email Address"/></td>
</tr>
<tr><th></th>
	<td><input type="hidden" name="type" value="1"/></td>
</tr>
<tr>
	<td colspan="2" align="center"><button type="reset">RESET</button><button type="submit" name="register" value="user_registration">SUBMIT</button></td>
</tr>
</table>
</form>
</fieldset>
</div>