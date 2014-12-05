<!--head.html include Goes Here -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<<jsp:include page = "/layout/head.jsp"/>
<!--body.html include Goes Here -->
<jsp:include page = "/layout/body.jsp"/>

<table id="details" summary="This table contains details about each course.">
<caption>
Schedule of Classes Course Details 
</caption>  
  <tr>
    <th scope="row">Semester:</th>
    <td>${semester} </td>
  </tr>
  <tr>
    <th scope="row">Course - Title:</th>
    <td>${course.discipline} ${course.courseNumber} - ${course.title}</td>
  </tr>
  <tr>
    <th scope="row">Code:</th>
    <td>${code}</td>
  </tr>
  <tr>
    <th scope="row">Section:</th>
    <td>${section}</td>
  </tr>
  <tr>
    <th scope="row">Department:</th>
    <td>${department}</td>
  </tr>
  <tr>
    <th scope="row">Division:</th>
    <td>${division}</td>
  </tr>
  <tr>
    <th scope="row">Dates:</th>
    <td>${begin} - ${end} </td>
  </tr>
  <tr>
    <th scope="row">Seats Available:</th>
    <td>${seat}</td>
  </tr>
  	<c:set var="days" value=" ${meeting}" />
  	<c:set var="hourb" value=" ${start}" />
  	<c:set var="houre" value=" ${stop}" />
  	<c:set var="where" value=" ${building}" />
  	<c:set var="roombuilding" value=" ${room}" />
  	<c:set var="professorname" value=" ${instructor}" />
  	<c:set var="daysarr" value="${fn:split(days, ', ')}" />
  	<c:set var="hourbarr" value="${fn:split(hourb, ', ')}" />
  	<c:set var="hourearr" value="${fn:split(houre, ', ')}" />
  	<c:set var="wherearr" value="${fn:split(where, ', ')}" />
  	<c:set var="roombuildingarr" value="${fn:split(roombuilding, ', ')}" />
  	<c:set var="professornamearr" value="${fn:split(professorname, ', ')}" />
  	<c:set var="hourdefine" value=" ${ampm}" />
      <c:set var="specific" value="${fn:split(hourdefine, ', ')}" /> 
  	<c:forEach var = "i" begin="0" end="${fn:length(daysarr)-1}">
  	<tr>
    <th scope="row">Meeting ${i+1} - Day &amp; Time, Building &amp; Room, Instructor: </th>
    <td>${daysarr[i]}, ${hourbarr[i]}${specific[i]} - ${hourearr[i]}${specific[i]}, ${wherearr[i]} ${roombuildingarr[i]} , ${professornamearr[i]}</td>
    </tr>
    </c:forEach>
  <tr>
    <th scope="row">Credit Hours: </th>
    <td> ${course.credit}</td>
  </tr>
  <tr>
    <th scope="row">Description:</th>
    <td>${course.description}</td>
  </tr>
  <tr>
    <th scope="row">Course Comments: </th>
    <td>${comments}</td>
  </tr>
  <tr>
    <th scope="row">Pre-requisite:</th>
    <td>${course.prereq}</td>
  </tr>
</table>

<!--foot.html include Goes Here -->
<jsp:include page = "/layout/foot.jsp"/>
