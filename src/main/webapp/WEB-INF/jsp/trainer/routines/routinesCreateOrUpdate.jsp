<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routines">
    <h2>New Routine for <c:out value="${client.firstName} ${client.lastName}"/></h2>

	<h3>Routine Data</h3>
	<form:form modelAttribute="routines" id="routineForm">

		<input type="text" id="name" name="name" placeholder="Name" required="required" value="${routine.name}">
		<br>
		<input type="text" id="description" name="description" placeholder="description" required="required" value="${routine.description}">
				
		<br>
		
		<input type="submit" value="Add Routine">
		
	</form:form>
    
</yogogym:layout>
