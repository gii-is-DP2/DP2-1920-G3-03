<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">

	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>

	<jsp:body>
	    <h2>New Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>
	
		<h3>Training Data</h3>
		<form:form modelAttribute="trainings" id="trainingForm">
	
			<input type="text" id="name" name="name" placeholder="Name" required="required" value="${training.name}">
			<br>
			<br>
			
			<label>Initial Date:</label>
			<br>
			<input type="text" id="initialDate" name="initialDate" required="required">
			<br>
			<br>
			<label>End Date:</label>
			<br>
			<input type="text" id="endDate" name="endDate" required="required">
			<br>
			<br>
			<input type="submit" value="Add Training">
			
		</form:form>
	
	</jsp:body>
</yogogym:layout>
