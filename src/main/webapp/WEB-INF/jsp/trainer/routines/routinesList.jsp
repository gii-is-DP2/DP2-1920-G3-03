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
		<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
			<spring:param name="clientId" value="${client.id}"/>
		</spring:url>
		<h3>Client <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/> </a> ( <c:out value="${client.email}"/> ): </h3>
		
		<ul>
			<c:forEach var="training" items="${client.trainings}">
				
				<li><c:out value="${training.name}"/></li>
				<ul>
					<c:forEach var="routine" items="${training.routines}">
						<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/routines/{routineId}" var="routineUrl">
		                        <spring:param name="routineId" value="${routine.id}"/>
		                </spring:url>
						<li><a href="${fn:escapeXml(routineUrl)}"><c:out value="${routine.name}"/></a></li>
					</c:forEach>
				</ul>
				
				<spring:url value="/trainer/${trainerUsername}/clients/{clientId}/trainings/${training.id}/routines/create" var="routineAddUrl">
					<spring:param name="clientId" value="${client.id}"/>
				</spring:url>
				<h3><a href="${fn:escapeXml(routineAddUrl)}">Add Routine</a></h3>
			
			</c:forEach>
		</ul>
				
	</div>
	</c:forEach>
    
</yogogym:layout>
