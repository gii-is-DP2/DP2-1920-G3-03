<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="mx-5	">	
	<c:choose>
		<c:when test="${empty exercise.equipment.name}">
			<p>Equipment: None</p>	
		</c:when>
		<c:otherwise>
			<p>Equipment: <c:out value="${exercise.equipment.name}"/></p>		
		</c:otherwise>
	</c:choose>
	<p>Repetition Type: <c:out value="${exercise.repetitionType}"/></p>	
</div>