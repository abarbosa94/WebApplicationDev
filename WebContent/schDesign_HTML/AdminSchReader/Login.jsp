<!--head.html include Goes Here -->
<!--body.html include Goes Here -->

<BODY BGCOLOR="white">
<center>
  	 <H1><font color="red">User ID and Password does not match!</font></H1>
<H2>Please Enter your User ID and Password</H2>
<br>
 
<FORM ACTION="Validation" METHOD = "POST">
  User  ID: 
  <INPUT TYPE="TEXT" NAME="userId" VALUE=""><p>
  Password:
  <INPUT TYPE="password" NAME="password" VALUE=""><p>
  <label>Department ID:
	<select name="deptId">
	<option selected="selected">Select Dept </option>
	<option value="1">Registrar</option>
	<option value="2">BCTC</option>
	<option value="3">Zicklin</option>

	</select>
  </label>
  <P>
  <INPUT TYPE="SUBMIT" value="Log In"> <!-- Press this button to submit form -->
</FORM>
</CENTER>

<!--foot.html include Goes Here -->


