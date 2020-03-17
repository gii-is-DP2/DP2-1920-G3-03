<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">
	<c:if test="${deleteMessage!=null}">
   		<div class="alert alert-success">
   			<p>${deleteMessage}</p>
   		</div>
   	</c:if>
    <h2>All Trainings</h2>

	<c:forEach var="client" items="${trainer.clients}">
	<div>
		
		<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
			<spring:param name="clientId" value="${client.id}"/>
		</spring:url>
		<h3> Client <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a> ( <c:out value ="${client.email}"/> )</h3>
		
		<ul>
			<c:forEach var="training" items="${client.trainings}">
				<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/{trainingId}" var="trainingUrl">
                        <spring:param name="trainingId" value="${training.id}"/>
                </spring:url>
                
                
                <c:choose>
                	<c:when test="${training.endDate < actualDate}">
                		<c:set var="trainingCompleted" value="COMPLETED"/>
                	</c:when>
                	<c:otherwise>
                		<c:set var="trainingCompleted" value="ON GOING"/>
                	</c:otherwise>
                </c:choose>
               
                
				<li><a href="${fn:escapeXml(trainingUrl)}"><c:out value="${training.name}"/></a> <c:out value="(${trainingCompleted})"/></li>
			</c:forEach>
		</ul>
		
		<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/create" var="trainingAddUrl" />
		<a href="${fn:escapeXml(trainingAddUrl)}">Add Training</a>		
		
	</div>
	<br>
	</c:forEach>
    
</yogogym:layout>
