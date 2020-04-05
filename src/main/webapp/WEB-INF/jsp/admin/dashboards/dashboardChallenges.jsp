<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script type="text/javascript">
function createUrl(){
    var action_src = "/admin/dashboardChallenges/" + document.getElementsByName("month")[0].value;
    var form = document.getElementById('form1');
    form.action = action_src ;
}
</script>


<petclinic:layout pageName="dashboards">

<form id="form1" onsubmit="createUrl()">
		
	<select id="month" name="month">
	  <option selected disabled hidden>Month</option>
	  <option value="1">Enero</option>
	  <option value="2">Febrero</option>
	  <option value="3">Marzo</option>
	  <option value="4">Abril</option>
	  <option value="5">Mayo</option>
	  <option value="6">Junio</option>
	  <option value="7">Julio</option>
	  <option value="8">Agosto</option>
	  <option value="9">Septiembre</option>
	  <option value="10">Octubre</option>
	  <option value="11">Noviembre</option>
	  <option value="12">Diciembre</option>
	</select>
	<input type="submit" value="Change"></input>

</form>

<c:choose>

	<c:when test="${!ChallengesExists}">
                <h2>There are no challenges ending this month. Create one!</h2>
                <a class="btn btn-default" href='<spring:url value="/admin/challenges/new" htmlEscape="true"/>'>Create Challenge</a>
	</c:when>


	<c:otherwise>
                <h2><c:out value="${test}"/></h2>
	</c:otherwise>
	
</c:choose>


</petclinic:layout>