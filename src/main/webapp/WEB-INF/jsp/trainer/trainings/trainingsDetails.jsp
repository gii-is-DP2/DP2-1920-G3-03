<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">

	<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
		<spring:param name="clientId" value="${client.id}"/>
	</spring:url>
	<h2>Trainer: Training Details of <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a></h2>
	<h3>Name: <c:out value="${training.name}"/></h3>
	<p><b>Starts:</b> <c:out value="${training.initialDate}"/></p>
	<p><b>Ends:</b> <c:out value="${training.endDate}"/></p>
	<br>
	<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/edit" var="trainingEditUrl" />
	<a href="${fn:escapeXml(trainingEditUrl)}">Edit Training</a>
	<br>
	<br>
	<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/delete" var="trainingDeleteUrl" />
	<a href="${fn:escapeXml(trainingDeleteUrl)}">Delete Training</a>
	
	<br>
	<br>
	<br>
		
	<h3>Routines</h3>
	<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/routines/create" var="routineAddurl" />
	
	<c:choose>
		<c:when test="${training.endDate < actualDate}">
			<h3><a style="color:grey">Add Routine</a></h3>
		</c:when>
		<c:otherwise>
			<h3><a href="${fn:escapeXml(routineAddurl)}">Add Routine</a></h3>		
		</c:otherwise>
	</c:choose>
	
	
	<ul>
		<c:forEach var="routine" items="${training.routines}">
			<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/routines/{routineId}" var="routineUrl">
	        	<spring:param name="routineId" value="${routine.id}"/>
	        </spring:url>
			<li><a href="${fn:escapeXml(routineUrl)}"><c:out value="${routine.name}"/></a></li>
		</c:forEach>
	</ul>

	<h3>Diet</h3>
	<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/{trainingId}/diets/create" var="dietAddurl" >
	<spring:param name="trainingId" value="${training.id}"/>
	</spring:url>

	<h3><a href="${fn:escapeXml(dietAddurl)}">Add Diet</a></h3>
	<ul>
		<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/{trainingId}/diets/{dietId}" var="dietUrl">
	       	<spring:param name="dietId" value="${training.diet.id}"/>
			<spring:param name="trainingId" value="${training.id}"/>
	    </spring:url>
		<li><a href="${fn:escapeXml(dietUrl)}"><c:out value="${training.diet.name}"/></a></li>
	</ul>
	
</yogogym:layout>
