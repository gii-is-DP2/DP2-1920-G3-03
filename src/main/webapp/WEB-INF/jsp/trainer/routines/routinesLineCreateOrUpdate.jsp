<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
						
			<yogogym:selectField className="selectedExercise" label="Exercise" name="exercise.id" size="" map="${exercises}"/>			
			
			<%-- SOME JQUERY TESTING --%>
			
			<script>
				$(document).ready(function(){
					
					var exerciseId = $("select.selectedExercise").children("option:selected").val();
					$('#sec').load('/trainer/routineLine/ExerciseDetails/' + exerciseId);
				
					$("select.selectedExercise").change(function(){
						var exerciseId = $(this).children("option:selected").val();
						
						$('#sec').load('/trainer/routineLine/ExerciseDetails/' + exerciseId);
							
						});
						
				    });
			</script>			
			
			<section style="margin-left:205px; width:400px" id="sec">			
			</section>
			
			<br>
			
			<input type="hidden" name="routineId" value="${routineId}"/>
			
			<yogogym:inputField label="Repetitions" name="reps"/>
			<yogogym:inputField label="Time" name="time"/>
			<yogogym:inputField label="Weight" name="weight"/>
			<yogogym:inputField label="Series" name="series"/>
			
				
			<br>

                <div class="col-sm-offset-2 col-sm-10">                    
                    <c:choose>
						<c:when test="${routineLine['new']}">		
						     <button class="btn btn-default" type="submit">Add Routine Line</button>
						</c:when>
						<c:otherwise>
							 <button class="btn btn-default" type="submit">Update Routine Line</button>
						</c:otherwise>
					</c:choose>
				</div>
            </div>
		
	</form:form>
    
</yogogym:layout>
