<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <h2>New Diet for <c:out value="${client.firstName} ${client.lastName}"/></h2>

	<h3>Diet Data</h3>
	<form:form modelAttribute="diets" id="dietForm">

		<input type="text" id="name" name="name" placeholder="Name" required="required" value="${diet.name}">
		<br>
		<br>
		
		<label>Description:</label>
		<br>
		<input type="text" id="description" name="description" required="required" value="${diet.description}">
		<br>
		<br>
		<label>Kcal:</label>
		<br>
		<input type="number" id="kcal" name="kcal" required="required" value="${diet.kcal}">
		<br>
		<br>
		<input type="submit" value="Add Diet">
		
	</form:form>
    
</yogogym:layout>
