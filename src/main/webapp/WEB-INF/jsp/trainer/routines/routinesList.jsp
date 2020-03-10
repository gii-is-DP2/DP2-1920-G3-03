<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routines">
    <h2>All Routines</h2>

	<c:forEach var="client" items="${trainer.clients}">
	<div>
		<h3><c:out value="Client ${client.firstName} ${client.lastName} ( ${client.email} ) :"/></h3>
		
		<ul>
			<c:forEach var="routine" items="${client.routines}">
				<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/routines/{routineId}" var="routineUrl">
                        <spring:param name="routineId" value="${routine.id}"/>
                </spring:url>
				<li><a href="${fn:escapeXml(routineUrl)}"><c:out value="${routine.name}"/></a></li>
			</c:forEach>
		</ul>
		
		<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/routines/create" var="routineAddUrl" />
		<a href="${fn:escapeXml(routineAddUrl)}">Add Routine</a>		
		
	</div>
	</c:forEach>
    
</yogogym:layout>
