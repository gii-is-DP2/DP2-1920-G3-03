<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routines">
    <h2>New Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>

	<h3>Training Data</h3>
	<form:form modelAttribute="trainings" id="trainingForm">

		<input type="text" id="name" name="name" placeholder="Name" required="required" value="${training.name}">
		<br>
		<br>
		
		<label>Initial Date:</label>
		<br>
		<input type="date" id="initial_date" name="initial_date" required="required" value="${training.initialDate}">
		<br>
		<br>
		<label>End Date:</label>
		<br>
		<input type="date" id="endDate" name="endDate" required="required" value="${training.endDate}">
		<br>
		<br>
		<input type="submit" value="Add Training">
		
	</form:form>
    
</yogogym:layout>
