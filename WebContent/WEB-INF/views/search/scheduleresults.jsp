<!--head.html include Goes Here -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page = "/layout/head.jsp"/>
<!--body.html include Goes Here -->
<jsp:include page = "/layout/body.jsp"/>


  <p>Search results are based on the following keywords:</p>
  <table id="criteria" summary="This table contains the search criteria. Results are listed in next table.">
    <tr>
      <td><strong>Semester</strong>: ${semester} </td>
      <td><strong>Days</strong>: ${days}</td>
    </tr>
    <tr>
      <td><strong>Department</strong>: ${dept}</td>
      <td><strong>Time</strong>: ${time}</td>
    </tr>
    <tr>
      <td><strong>Discipline</strong>: ${discipline}</td>
      <td><strong>Course number</strong>: ${coursenum}</td>
    </tr>
    <tr>
      <td><strong>Division</strong>: ${division}</td>
      <td><strong>Instructor</strong>: ${instructor} </td>
    </tr>
    </table>
  <font color="red">
  <p><b>The schedule was LAST&nbsp; updated at ${lastupdate}</b></p>
  <p>Due to the dynamic nature of the registration process, not all courses listed as open will have space for new registrants.</p>
  </font>
<table id="results" summary="This table contains the search results for schedule of classes.">
  <caption>
  Schedule of Classes Search Results
  </caption>
  <thead>
    <tr>
      <th scope="col">Course</th>
      <th scope="col">Code</th>
      <th scope="col">Section</th>
      <th scope="col">Day &amp; Time </th>
      <th scope="col">Dates</th>
      <th scope="col">Bldg &amp; Rm </th>
      <th scope="col">Instructor</th>
      <th scope="col">Seats Avail </th>
      <th scope="col">Comments</th>
    </tr>
  </thead>
  <tbody >
    
    <c:forEach items="${results}" var="result">	
    <tr>
    	<td>
    		<c:set var="courses">  
               <c:url value="courseDetails?">    
                  <c:param name="semester" value="${semester}"/>    
                  <c:param name="subject" value="${result.discipline}"/>    
                  <c:param name="number" value="${result.courseNum}"/>
                  <c:param name="code" value="${result.courseCode}"/> 
                  <c:param name="section" value="${result.courseSec}"/>
                  <c:set var="date">
                	<fmt:formatDate value="${result.startDate.time}" pattern="MM/dd/yyyy" />
                  </c:set>
                  <c:param name = "begin" value ="${date}"/>
                   <c:set var="datef">
                	<fmt:formatDate value="${result.endDate.time}" pattern="MM/dd/yyyy" />
                  </c:set>
                  <c:param name="end" value="${datef}"/>
                  <c:param name="meeting" value="${result.meetingDays}"/>
                  <c:param name="start" value="${result.startTime}"/>
                  <c:param name="stop" value="${result.stopTime}"/>
                  <c:param name="ampm" value="${result.ampm}"/>
                  <c:param name="building" value="${result.building}"/> 
                  <c:param name="room" value="${result.room}"/>
                  <c:param name="seats" value="${result.seats}"/>
                  <c:param name="instructor" value="${result.name}"/>
                  <c:param name="dep" value="${dept}"/>
                  <c:param name="comments" value="${result.comments}"/>  
                </c:url>    
          	</c:set>  
            <a href="${courses}">${result.discipline} ${result.courseNum}</a>   
    	</td>
      <td>${result.courseCode}</td>
      <td>${result.courseSec}</td>
      <c:set var="Days" value=" ${result.meetingDays}" />
      <c:set var="meetings" value="${fn:split(Days, ', ')}" />
      <c:set var="startTime" value=" ${result.startTime}" />
      <c:set var="hourBegin" value="${fn:split(startTime, ', ')}" />
      <c:set var="endTime" value=" ${result.stopTime}" />
      <c:set var="hourFinish" value="${fn:split(endTime, ', ')}" />
      <c:set var="Days" value=" ${result.meetingDays}" />
      <c:set var="meetings" value="${fn:split(Days, ', ')}" />
      <c:set var="hourdefine" value=" ${result.ampm}" />
      <c:set var="specific" value="${fn:split(hourdefine, ', ')}" />    
      <td>
      <c:forEach var = "i" begin="0" end="${fn:length(meetings)-1}">
      <br>
      ${meetings[i]} ${hourBegin[i]}${specific[i]}-${hourFinish[i]}${specific[i]}        
       </c:forEach>
       
       </td>
      <td><fmt:formatDate value="${result.startDate.time}" pattern="MM/dd/yyyy" /> - 
      <fmt:formatDate value="${result.endDate.time}" pattern="MM/dd/yyyy" /> </td>
      <c:set var="buildings" value=" ${result.building}" />
      <c:set var="difbuild" value="${fn:split(buildings, ', ')}" />
      <c:set var="rooms" value=" ${result.room}" />
      <c:set var="difroom" value="${fn:split(rooms, ', ')}" />
      <td>
      <c:forEach var = "i" begin="0" end="${fn:length(meetings)-1}">
      <br>
      ${difbuild[i]} ${difroom[i]}
       </c:forEach> 
       </td>
       <c:set var="instructorsNames" value=" ${result.name}" />
       <c:set var="instructorsArr" value="${fn:split(instructorsNames, ', ')}" />
       <td>
       <c:forEach var = "i" begin="0" end="${fn:length(instructorsArr)-1}">
      <br>
      ${instructorsArr[i]} ${InstructorsArr[i]}
       </c:forEach> 
       </td>
      <td>${result.seats}</td>
      <td>${result.comments}</td>
      </tr>
      </c:forEach>
  </tbody>
</table>
<!--foot.html include Goes Here -->
<jsp:include page = "/layout/foot.jsp"/>

