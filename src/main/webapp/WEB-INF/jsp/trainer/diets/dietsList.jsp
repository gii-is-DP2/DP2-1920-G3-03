<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <h2>All Diets</h2>

	<c:forEach var="client" items="${trainer.clients}">
	<div>
		
		<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
			<spring:param name="clientId" value="${client.id}"/>
		</spring:url>
		<h3> Client <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a> ( <c:out value ="${client.email}"/> )</h3>
		
		<ul>
			<c:forEach var="diet" items="${client.diets}">
				<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/diets/{dietId}" var="dietUrl">
                        <spring:param name="dietId" value="${diet.id}"/>
                </spring:url>
				<li><a href="${fn:escapeXml(dietUrl)}"><c:out value="${diet.name}"/></a></li>
			</c:forEach>
		</ul>
		
		<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/diets/create" var="dietAddUrl" />
		<a href="${fn:escapeXml(dietAddUrl)}">Add Diet</a>		
		
	</div>
	</c:forEach>
    
</yogogym:layout>
