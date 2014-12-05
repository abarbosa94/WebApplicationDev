<!--head.html include Goes Here -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<jsp:include page = "/layout/head.jsp"/>
<!--body.html include Goes Here -->
<jsp:include page = "/layout/body.jsp"/>
<script type="text/javascript">
	function find(id) {
		var dept = $("select#department").val();
		$.get("findDiscipline", {"id":id}, function(response) {
			var select = $('#discipline');
	        select.find('option').remove();
        	if(response.length==0){
        		$('<option>').val(0).text("Select All").appendTo(select);
        	}
        	else{
        		$.each(response, function(index, value) { 
    	        	var item = value.discAbreviation;
    	        	var name = value.disciplineName;
    	          $('<option>').val(item).text(name).appendTo(select);
    			});
        	}  
		});
	}
	
</script>

<script type="text/javascript">
	function finalizaAgora(id) {
    	$.get("findDiscipline?id=" + id);
  	}
  </script>
<form method="post" action="findResult">
	<div align="center">
      <p class="errormsg"><b><font color="#FF0000">${errormsg}
		</font></b> </p>
    <div align="center">
      <p>Enter the professor's name, discipline, course number and/or days you wish to search.
      </p>
      <table id="search" summary="This table contains search options for the schedule of classes.">
	  <tbody>
        <tr>
          <th><label for="semester">Semester:</label></th>
          <td><select id="semester" name="sem">
          	<c:forEach items="${semester}" var="semester">
          	 <option value="${semester.semesterName}"> ${semester.semesterName} (
          	 <fmt:formatDate value="${semester.startDate.time}" pattern="MM/dd/yyyy" /> to 
          	 <fmt:formatDate value="${semester.endDate.time}" pattern="MM/dd/yyyy" />
          	 )
          	 </option>
			  </c:forEach>
          </select></td>
        </tr>
        <tr>
          <th>Dept:</th>
          <td><select onClick = "find(this.value)" id = department name="department" size="1">
			<option value="00">Select	All</option>
			<c:forEach items="${dept}" var="dept">
			<option value="${dept.deptID}" >${dept.deptName}</option>
			</c:forEach>
			</select></td>
        </tr>
        <tr>
          <th>Discipline:</th>
          <td><select id = "discipline"  name="discipline" size="1">
          <option value="0" >Select All</option> 
			</select></td>
        </tr>
        <tr>
          <th>Division</th>
          <td>
            <label for="undergraduate">Undergraduate </label><input type="checkbox" id="undergraduate" value="U" name="div_undr" checked>
            <br>
            <label for="graduate">Graduate</label><input type="checkbox" id="gradaute" value="G" name="div_grad" checked>
          </td>
        </tr>
        <tr>
          <th><label for="number">Course number:</label></th>
          <td><input id="number" size="10" name="number" maxlength="5" type="text"></td>
        </tr>
        <tr>
          <th><label for="days">Days:</label></th>
          <td><select id="days" name="week">
              <option value="">Select	All </option>
              <option value="M">Mon </option>
              <option value="MTW">Mon-Tue-Wed </option>
              <option value="MTWF">Mon-Tue-Wed-Fri </option>
              <option value="MTWTH">Mon-Tue-Wed-Thr </option>
              <option value="MW">Mon-Wed </option>
              <option value="MWTH">Mon-Wed-Thr </option>
              <option value="MTH">Mon-Thr </option>
              <option value="T">Tue </option>
              <option value="TWF">Tue-Wed-Fri </option>
              <option value="TWTH">Tue-Wed-Thu </option>
              <option value="TTH">Tue-Thr </option>
              <option value="TF">Tue-Fri </option>
              <option value="W">Wed </option>
              <option value="TH">Thr </option>
              <option value="F">Fri </option>
              <option value="S">Sat </option>
              <option value="SU">Sun </option>
          </select></td>
        </tr>
        <tr>
          <th><label for="time">Time:</label></th>
          <td><select id="time" name="time_a_b">
              <option value="">Select	All </option>
              <option>before </option>
              <option>after </option>
              <option>around </option>
            </select>
            <select name="time">
              <option value="">Select	All </option>
              <option value="7">7:00am </option>
              <option value="8">8:00am </option>
              <option value="9">9:00am </option>
              <option value="10">10:00am </option>
              <option value="11">11:00am </option>
              <option value="12">12:00pm </option>
              <option value="13">1:00pm </option>
              <option value="14">2:00pm </option>
              <option value="15">3:00pm </option>
              <option value="16">4:00pm </option>
              <option value="17">5:00pm </option>
              <option value="18">6:00pm </option>
              <option value="19">7:00pm </option>
              <option value="20">8:00pm </option>
              <option value="21">9:00pm </option>
            </select>          </td>
        </tr>
        <tr>
          <th><label for="instructor">Instructor:</label></th>
          <td><input id="instructor" size="30" name="prof" type="text"></td>
        </tr>
      </tbody>
      </table>
    </div>
    <p align="center">
      <input value="Start Search" type="submit">
   </p>
   </form>

<!--foot.html include Goes Here -->
<jsp:include page = "/layout/foot.jsp"/>