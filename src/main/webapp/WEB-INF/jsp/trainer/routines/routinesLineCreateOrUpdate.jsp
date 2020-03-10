<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routinesLines">
    <h2>New Routine Line for <c:out value="${routine.name} (${client.firstName} ${client.lastName})"/></h2>

	<h3>Routine Line Data</h3>
	<form:form modelAttribute="routines_lines" id="routineLineForm">
		<input type="hidden" name="routine_id" value="${routine.id}">
		<input type="number" min="1" id="reps" name="reps" placeholder="Repetitions" required="required">
		<br>
		<input type="number" min="0" id="weight" name="weight" placeholder="Weight" required="required">
		<h3>Pick Exercise</h3>
		
		<select id="exercise" name="exercise" required="required">
			<c:forEach var="exercise" items="${exercises}">
				<option value="${exercise.id}">${exercise.name}</option>
			</c:forEach>
		</select>		
		<br>
		
		<input type="submit" value="Add Routine Line">
		
	</form:form>
    
</yogogym:layout>
