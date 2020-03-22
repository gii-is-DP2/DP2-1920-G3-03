<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="exercises">

    <h2>Exercise Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${exercise.name}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${exercise.description}"/></td>
        </tr>
        <tr>
            <th>Kcal</th>
            <td><c:out value="${exercise.kcal}"/></td>
        </tr>
        <tr>
            <th>Intensity</th>
            <td><c:out value="${exercise.intensity}"/></td>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>
    <h2>Equipment</h2>
	
	<c:choose>
		<c:when test="${empty exercise.equipment}">
			<h3>None</h3>
		</c:when>
		<c:otherwise>
		    <table class="table table-striped">
		            <tr>
			           <td>
		                    <spring:url value="/mainMenu/equipments/{equipmentId}" var="equipmentUrl">
		                        <spring:param name="equipmentId" value="${exercise.equipment.id}"/>
		                    </spring:url>
		                    <a href="${fn:escapeXml(equipmentUrl)}"><c:out value="${exercise.equipment.name}"/></a>
		                </td>
			        </tr>
		    </table>
		</c:otherwise>
	</c:choose>

</petclinic:layout>
