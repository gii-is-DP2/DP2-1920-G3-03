<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routineLine">

	<c:choose>
		<c:when test="${routineLine['new']}">		
		    <h2>New Routine Line for <c:out value="${routine.name} (${client.firstName} ${client.lastName})"/></h2>
		</c:when>
		<c:otherwise>
			<h2>Update Routine Line for <c:out value="${routine.name} (${client.firstName} ${client.lastName})"/></h2>
		</c:otherwise>
	</c:choose>

	<h3>Routine Line Data</h3>
	<form:form modelAttribute="routineLine" class="form-horizontal">
		<div class="form-group has-feedback">
			
			<input type="hidden" name="routineId" value="${routineId}"/>
			
			<yogogym:inputField label="Repetitions" name="reps"/>
			<yogogym:inputField label="Time" name="time"/>
			<yogogym:inputField label="Weight" name="weight"/>
			<yogogym:inputField label="Series" name="series"/>
			
			<h3>Pick Exercise</h3>
						
			<yogogym:selectField label="Exercise" name="exercise.id" size="" map="${exercises}"/>			
				
			<br>
			<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">                    
                    <c:choose>
						<c:when test="">		
						     <button class="btn btn-default" type="submit">Add Routine Line</button>
						</c:when>
						<c:otherwise>
							 <button class="btn btn-default" type="submit">Update Routine Line</button>
						</c:otherwise>
					</c:choose>
				</div>
            </div>
		</div>
	</form:form>
    
</yogogym:layout>
