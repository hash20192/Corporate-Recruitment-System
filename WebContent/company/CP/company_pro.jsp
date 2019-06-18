		<form action="Company_Controller">
			<table cellspacing="10px" cellpadding="10px">
				<tr>
					<th>First Name</th>
					<td><input type="text" name="emr_firstname" /></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="emr_lastname" /></td>
				</tr>
				<tr>
					<th>Mobile No</th>
					<td><input type="text" name="emr_mobileno" /></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="text" name="emr_email" /></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td><input type="radio" name="emr_gender" value="male" /> Male
						<br> <input type="radio" name="emr_gender" value="female" />
						Female</td>
				</tr>

				<tr>
					<th>Nationality</th>
					<td><select name="emr_nationality">
							<option	value="indian">Indian</option>
							<option value="American">American</option>
							<option value="African">African</option>
					</select></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><textarea rows="4" cols="5" name="emr_address"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center">Company Details</td>
				</tr>
				<tr>
					<th>Company Name</th>
					<td><input type="text" name="company_name" /></td>
				</tr>
				<tr>
					<th>Company Id</th>
					<td><input type="text" name="company_id" /></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="text" name="company_password" /></td>
				</tr>
				<tr>
					<th>Re-Password</th>
					<td><input type="text" name="company_repassword" /></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="text" name="company_email" /></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><textarea rows="4" cols="5" name="company_address"></textarea></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="hidden" name="type" value="1" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="reset">RESET</button>
						<button type="submit" name="register" value="company_registration">SUBMIT</button></td>

				</tr>
			</table>
		</form>