<%/*************************************
*
* Student Name: Andre Barbosa
* Assingment: #3
* Class: CIS 4160
* Features included: input verification
*
*
************************************/%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*,
				 java.io.*,
				 javax.servlet.*,
				 javax.servlet.http.*" %>
<%@ include file="username.jsp" %>
<html>
<head>
<title> Baruch Course Schedule </title>
<jsp:useBean id = "user" class="com.webapp.hw.andre.DAO.JdbcAdminDao" scope="session"/>
</head>
<body bgcollor = "white">
<!-- Security reasons (using session) -->
<c:if test="${user.getUser()==null}">
	<c:redirect url = "Login.html"/>
</c:if>
<center>
	<h1> Welcome to Update Baruch Course Schedule </h1>
	<br>
	<% 
		Connection conn = null;
		try {
		 // 1. Load the driver
			Class.forName(driver);

  // myusername and mypassword are declared iun a file called username.jsp
  
   // 2. Define the connection URL
   // 3. Establish the connection
  			conn = DriverManager.getConnection(
            url ,
            myusername,
            mypassword);
            
         
  // 4. Create a statement object
  			Statement stmt = conn.createStatement();

  // 5. Execute a query
  			ResultSet rs = stmt.executeQuery("SELECT SEMESTER_NAME FROM SEMESTER_SR");
  	%>
  	<form action = "display.jsp" method = "POST">
		<label> Semester:
		<select name="semester"> 
		<option selected="selected">Semester Select</option>
  	<% 

		  while(rs.next()) {
		  %>
		  <option select = "selected" ><%out.println(rs.getString("SEMESTER_NAME")); %></option> 	
		 <% }%> 
  		</select>
  		</label>&nbsp;&nbsp;
 	 	<%
		}
		
		catch(SQLException e)
		   {
		      // Do exception catch such as if connection is not made or 
		      // query is not set up properly
		      out.println("SQLException: " + e.getMessage() + "<BR>");
		      while((e = e.getNextException()) != null)
		         out.println(e.getMessage() + "<BR>");
		   }
		   catch(ClassNotFoundException e)
		   {
		      out.println("ClassNotFoundException: " + e.getMessage() + "<BR>");
		   }
		   finally
		   {
		// 7. Close connection; Clean up resources
		      if(conn != null)
		      {
		         try
		         {
		            conn.close();
		         }
		         catch (Exception ignored) {}
		      }
		   }
	%>
	
		
		Start Date: 
  		<INPUT TYPE="TEXT" NAME="startDate" VALUE="">&nbsp;&nbsp;&nbsp;
  		End Date:</B>
  		<INPUT TYPE="TEXT" NAME="endDate" VALUE=""><br><br><br>
		<input TYPE="SUBMIT" value="Update Schedule"> <!-- Press this button to submit form -->
	</form>
</center>

</body>
</html>