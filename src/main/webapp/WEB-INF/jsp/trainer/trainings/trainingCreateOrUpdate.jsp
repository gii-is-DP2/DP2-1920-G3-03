<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routines">
	
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
	<jsp:body>
		<c:choose>
	            <c:when test="${training['new']}">
	                <h2>New Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>
	            </c:when>
	            <c:otherwise>
	                <h2>Editing Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>
	            </c:otherwise>
	        </c:choose>
	
		<h3>Training Data</h3>
		<form:form modelAttribute="trainings" id="trainingForm">
	
			<input type="text" id="name" name="name" placeholder="Name" required="required" value="${training.name}">
			<br>
			<br>
			
			<label>Initial Date:</label>
			<br>
			<input type="text" id="initialDate" name="initialDate" placeholder="yyyy/MM/dd" required="required" value="${training.initialDate}" readonly="${!training['new']}">
			<br>
			<br>
			<label>End Date:</label>
			<br>
			<input type="text" id="endDate" name="endDate" placeholder="yyyy/MM/dd" required="required" value="${training.endDate}">
			<br>
			<br>
			<c:choose>
	            <c:when test="${training['new']}">
	                <input type="submit" value="Add Training">
	            </c:when>
	            <c:otherwise>
	                <input type="submit" value="Update Training">
	            </c:otherwise>
	        </c:choose>
		</form:form>
	</jsp:body>
    
</yogogym:layout>
