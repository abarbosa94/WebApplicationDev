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
<%@ page import="java.sql.*" %>
<%@ page import = "java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ include file="username.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Baruch College Schedule Updated</title>
</head>
<body>
<center>
<h1>You have successfully updated the semester schedule</h1>
</center>


<p align="left">
	 <b>
	 <a href="logout.jsp"><font size="5">Log out</font></a> </b>&nbsp;<br>

<br>
</p>
<center>
<table width=600 border=1>
      <tr>
	<th align=left>Semester Name</th>
        <th align=left>Start Date</th>
        <th align=left>End Date</th>
     </tr>
        <tr>
	  <td><%
		String result = request.getParameter("semester");
		out.println(result);
		String dateStr = request.getParameter("startDate");  
		String dateEnd = request.getParameter("endDate");
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		Date  datea = null;
		Date  dateb = null;
		try {
		    if(!(dateStr.equals(""))) {
	            datea = formater.parse(dateStr);
	        }
	        if(!(dateEnd.equals(""))) {   	
	        	dateb = formater.parse(dateEnd);
	        }
		}
		catch(ParseException e) {
		    out.println("Error: " + e.getMessage() + "<BR>");
		}
        
	%></td>
	<% Connection conn2 = null;
		try {
		 // 1. Load the driver
		 			Class.forName(driver);

		 		  // myusername and mypassword are declared iun a file called username.jsp
		 		  
		 		   // 2. Define the connection URL
		 		   // 3. Establish the connection
		 		  conn2 = DriverManager.getConnection(
		 		            url ,
		 		            myusername,
		 		            mypassword);
		 		            
		 		         
		 		  // 4. Create a statement object
		 		  Statement stmt = conn2.createStatement();
		 		  Statement stmt2 = conn2.createStatement();
		 		 ResultSet rs = stmt2.executeQuery("SELECT START_DATE, END_DATE FROM SEMESTER_SR WHERE SEMESTER_NAME = '"+result+"'");
		 		 if(!rs.next()) {
				      out.println("The selected option"+" dont exists in the database !");
				  }
		 		 else {
		 		 // 5. Execute a query
			 		 if(!dateStr.equals("")&&(!dateEnd.equals(""))) {
			 		     if(datea.after(dateb)) throw new SQLException("Start Date is greater than End Date");
			 		 }
			 		  if(!dateStr.equals("")) {
			 		      if(datea.before(rs.getDate("END_DATE"))) {
			 		      	stmt.executeUpdate("UPDATE SEMESTER_SR SET START_DATE=TO_DATE('"+dateStr+"', 'MM/DD/YYYY') WHERE SEMESTER_NAME = '"+result+"'" );
			 		      }
			 		      else {
			 		         throw new SQLException();
			 		      }
			 		  }
			 		  if(!dateEnd.equals("")) {
			 		      if(dateb.after(rs.getDate("START_DATE"))) {
			 		     	stmt.executeUpdate("UPDATE SEMESTER_SR SET END_DATE=TO_DATE('"+dateEnd+"', 'MM/DD/YYYY') WHERE SEMESTER_NAME = '"+result+"'" );
			 		  	}
			 		      else {
			 		         throw new SQLException();   
			 		      }
		 		 	}
		 		 }
		 		 
		}
		catch(SQLException e) {
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
	        if(conn2 != null)
	        {
	           try
	           {
	              conn2.close();
	           }
	           catch (Exception ignored) {}
	        }
	     }
	
	%>
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
		  ResultSet rs = stmt.executeQuery("SELECT START_DATE, END_DATE FROM SEMESTER_SR WHERE SEMESTER_NAME = '"+result+"'");
		  
		  if(!rs.next()) {
		      out.println("The selected option"+" dont exists in the database !");
		  }
		  else {
		      if(rs.getDate("START_DATE").before(rs.getDate("END_DATE"))) {
		          out.println("<td>" + rs.getDate("START_DATE") + "</td>");
		          out.println("<td>" + rs.getDate("END_DATE") + "</td>");
		      }
		      //check if the start date is bigger than the end date
		      else { 
 		          throw new SQLException("Start date is greater than End date");
		      }
		  }
		}
		
		catch(SQLException e) {
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
          
        </tr>
</table>
</center>


<!--foot.html include Goes Here -->
</body>
</html>