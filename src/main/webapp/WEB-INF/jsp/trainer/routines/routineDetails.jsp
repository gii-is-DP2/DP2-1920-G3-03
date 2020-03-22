<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
	<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
		<spring:param name="clientId" value="${client.id}"/>
	</spring:url>
	<h2>Trainer: Routine Details of <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a></h2>
	<p><b>Routine Name:</b> <c:out value="${routine.name}"/></p>
	<p><b>Description:</b> <c:out value="${routine.description}"/></p>
	<p><b>Repetitions Per Week:</b> <c:out value="${routine.repsPerWeek}"/></p>
	<br>
	<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/${training.id}/routines/${routine.id}/edit" var="routineEditUrl" />
	<a href="${fn:escapeXml(routineEditUrl)}">Edit Routine</a>
	<br>
	<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/${training.id}/routines/${routine.id}/delete" var="routineDeleteAddUrl" />
	<a href="${fn:escapeXml(routineDeleteAddUrl)}">Delete Routine</a>
	<br>
	<br>
	
	<h2>Line of Routines</h2>
	<table class="table table-striped">
		<thead>
        <tr>
        	<th style="text-align:center">Line</th>
            <th style="text-align:center">Repetitions</th>
            <th style="text-align:center">Time</th>
            <th style="text-align:center">Series</th>
            <th style="text-align:center">Weight</th>
            <th>Exercise</th>
			<th>Description</th>
			<th>Kcal</th>
			<th>Edit</th>
			<th>Delete</th>
        </tr>
        </thead>
	<c:forEach var="lineRoutine" items="${routine.routineLine}">
			 <tr>
			 	<td style="text-align:center"><c:out value="${lineRoutine.id}"/></td>
	            <td style="text-align:center">
	            	<c:choose>
	            		<c:when test="${!empty lineRoutine.reps}">
	            			<c:out value="${lineRoutine.reps}"/>
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value="-"/>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            <td style="text-align:center">
	            	<c:choose>
	            		<c:when test="${!empty lineRoutine.time}">
	            			<c:out value="${lineRoutine.time}"/>
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value="-"/>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            <td style="text-align:center"><c:out value="${lineRoutine.series}"/></td>
	        	<td style="text-align:center"><c:out value="${lineRoutine.weight}"/></td>
	        	<td>
				<spring:url value="/mainMenu/exercises/{exerciseId}" var="exerciseUrl">
                       <spring:param name="exerciseId" value="${lineRoutine.exercise.id}"/>
                   </spring:url>
				<a href="${fn:escapeXml(exerciseUrl)}"><c:out value="${lineRoutine.exercise.name}"/></a>
				</td>
				<td><c:out value="${lineRoutine.exercise.description}"/></td>
				<td><c:out value="${lineRoutine.exercise.kcal}"/></td>
				
				<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/${training.id}/routines/${routine.id}/routineLine/{routineLineId}/update" var="routineLineUpdateUrl">
					<spring:param name="routineLineId" value="${lineRoutine.id}"/>
				</spring:url>	
				<td><a href="${fn:escapeXml(routineLineUpdateUrl)}">Edit</a></td>
				
				<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/${training.id}/routines/${routine.id}/routineLine/{routineLineId}/delete" var="routineLineDeleteUrl">
					<spring:param name="routineLineId" value="${lineRoutine.id}"/>
				</spring:url>				
				<td><a href="${fn:escapeXml(routineLineDeleteUrl)}">Delete</a></td>
	        </tr>
	</c:forEach>
	</table>
		
	<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/${training.id}/routines/${routine.id}/routineLine/create" var="routineLineAddUrl" />
	<a href="${fn:escapeXml(routineLineAddUrl)}">Add Routine Line</a>
	
</yogogym:layout>
