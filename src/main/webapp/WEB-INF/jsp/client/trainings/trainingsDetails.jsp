<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
		
	<c:if test="${error != null}">
		<div class="text-center alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  		<span class="sr-only">Error:</span>
   			${error}
   		</div>
	</c:if>
	
	<c:if test="${deleteRoutineLine != null}">
		<div class="text-center alert alert-success" role="alert">
			<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
	  		<span class="sr-only">Success:</span>
   			${deleteRoutineLine}
   		</div>
	</c:if>
	
	<c:if test="${updateRoutineLine != null}">
		<div class="text-center alert alert-info" role="alert">
			<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
	  		<span class="sr-only">Success:</span>
   			${updateRoutineLine}
   		</div>
	</c:if>
	
	<c:if test="${deleteRoutine != null}">
		<div class="text-center alert alert-success" role="alert">
			<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
	  		<span class="sr-only">Success:</span>
   			${deleteRoutine}
   		</div>
	</c:if>

	<h2>My Trainings: <c:out value="${training.name}"/></h2>
	
	<p><b>Starts:</b> <c:out value="${training.initialDate}"/></p>
	<p><b>Ends:</b> <c:out value="${training.endDate}"/></p>
	<p><b>Editing Permission:</b> <c:out value="${training.editingPermission}"/></p>
	<br>
	
	<c:choose>
		<c:when test="${training.editingPermission!='TRAINER'}">
			<spring:url value="/client/${client.user.username}/trainings/${training.id}/edit" var="trainingEditUrl" />
			<a href="${fn:escapeXml(trainingEditUrl)}">Edit Training</a>
		</c:when>
		<c:otherwise>
			<p><a style="color:grey">Edit Training</a></p>
		</c:otherwise>
	</c:choose>

    <br> 
    	<form action="https://accounts.spotify.com/authorize">
    		<input type="hidden" name ="redirect_uri" value = "http://localhost:8080/callback/"/>
    		<input type="hidden" name ="client_id" value = "956b8ae3e4b246b6a82c4a2c5ce6e4ac"/>
    		<input type = "hidden" name = "response_type" value ="code"/>
    		<input type = "hidden" name = "training_id" value="${training.id}"/>
    		<button class="btn btn-default">Check playlist for this training</button>
    	</form>
	<br>    
      
    <c:choose>
		<c:when test="${training.endDate < actualDate || training.editingPermission!='TRAINER'}">
			<spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/create" var="addRoutineUrl"/>
			<a href="${fn:escapeXml(addRoutineUrl)}">Add Routine</a>
		</c:when>
		<c:otherwise>
			 <p><a style="color:grey">Add Routine</a></p>
		</c:otherwise>
	</c:choose>
    
    <br>
    <br>
  
    <c:forEach var="routine" items="${training.routines}">
    	
    	<h3>Routine: <c:out value="${routine.name}"/></h3>
    	<h3>Repetitions per Week: <c:out value="${routine.repsPerWeek}"/></h3>
    	
    	<c:choose>
			<c:when test="${training.endDate < actualDate || training.editingPermission!='TRAINER'}">
	    		<spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/${routine.id}/update" var="updateRoutineUrl"/>
	    		<a href="${fn:escapeXml(updateRoutineUrl)}">Edit Routine</a>
    		</c:when>
    		<c:otherwise>
    			<a style="color:grey">Edit Routine</a>
    		</c:otherwise>
    	</c:choose>
    	
    	<br>
    	
    	<c:choose>
			<c:when test="${training.endDate < actualDate || training.editingPermission!='TRAINER'}">
		    	<spring:url value="/client/${client.user.username}/trainings/${training.id}/routine/${routine.id}/delete" var="deleteRoutineUrl"/>
		    	<a href="${fn:escapeXml(deleteRoutineUrl)}">Delete Routine</a>
    		</c:when>
    		<c:otherwise>
    			<a style="color:grey">Delete Routine</a>
    		</c:otherwise>
    	</c:choose>
    	
    	<br>
    	
    	<c:choose>
			<c:when test="${training.endDate < actualDate || training.editingPermission!='TRAINER'}">
		    	<spring:url value="/client/${client.user.username}/trainings/${training.id}/routines/${routine.id}/routineLine/create" var="addRoutineLineUrl"/>
		    	<a href="${fn:escapeXml(addRoutineLineUrl)}">Add Routine Line</a>
			</c:when>
			<c:otherwise>
    			<a style="color:grey">Add Routine Line</a>
    		</c:otherwise>
		</c:choose>		
		
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
						<c:when test="${training.endDate < actualDate || training.editingPermission=='TRAINER'}">
								
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
