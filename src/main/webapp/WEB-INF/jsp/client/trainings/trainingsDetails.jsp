<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
	<h2>My Trainings: <c:out value="${training.name}"/></h2>
    
    <spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/create" var="addRoutineUrl"/>
    <a href="${fn:escapeXml(addRoutineUrl)}">Add Routine</a>
    <br>
    <br>
    <c:forEach var="routine" items="${training.routines}">
    	
    	<h3>Routine: <c:out value="${routine.name}"/></h3>
    	<h3>Repetitions per Week: <c:out value="${routine.repsPerWeek}"/></h3>
    	<spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/${routine.id}/update" var="updateRoutineUrl"/>
    	<a href="${fn:escapeXml(updateRoutineUrl)}">Edit Routine</a>
    	<br>
    	<spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/${routine.id}/delete" var="deleteRoutineUrl"/>
    	<a href="${fn:escapeXml(deleteRoutineUrl)}">Delete Routine</a>
    	<br>
    	<spring:url value="/client/${client.user.username}/trainings/${training.id}/routines/${routine.id}/routineLine/create" var="addRoutineLineUrl"/>
    	<a href="${fn:escapeXml(addRoutineLineUrl)}">Add Routine Line</a>
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
					
					<c:choose>
						<c:when test="${training.endDate < actualDate}">
								
							<td><a style="color:grey">Edit</a></td>
										
							<td><a style="color:grey">Delete</a></td>
							
						</c:when>
						<c:otherwise>
							
							<spring:url value="/client/${client.user.username}/trainings/${training.id}/routines/${routine.id}/routineLine/{routineLineId}/update" var="routineLineUpdateUrl">
								<spring:param name="routineLineId" value="${lineRoutine.id}"/>
							</spring:url>	
							<td><a href="${fn:escapeXml(routineLineUpdateUrl)}">Edit</a></td>
							
							<spring:url value="/client/${client.user.username}/trainings/${training.id}/routines/${routine.id}/routineLine/{routineLineId}/delete" var="routineLineDeleteUrl">
								<spring:param name="routineLineId" value="${lineRoutine.id}"/>
							</spring:url>				
							<td><a href="${fn:escapeXml(routineLineDeleteUrl)}">Delete</a></td>
							
						</c:otherwise>
					</c:choose>			
		        </tr>
		</c:forEach>
		</table>
    	
    </c:forEach>
        
</yogogym:layout>
