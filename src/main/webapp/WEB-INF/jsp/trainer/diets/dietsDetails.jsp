<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">

	<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
		<spring:param name="clientId" value="${client.id}"/>
	</spring:url>
	<h2>Trainer: Diet Details of <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a></h2>
	<h3>Client params: </h3>
	<p><b>weight:</b> <c:out value="${client.weight}"/></p>
	<p><b>height:</b> <c:out value="${client.height}"/></p>
	<p><b>age:</b> <c:out value="${client.age}"/></p>
	<p><b>fatPercentage:</b> <c:out value="${client.fatPercentage}"/></p>
	<br>
	<h3>Diet name: <c:out value="${diet.name}"/></h3>
	<p><b>Description:</b> <c:out value="${diet.description}"/></p>
	<p><b>Type:</b> <c:out value="${diet.type}"/></p>
	<p><b>Kcal:</b> <c:out value="${diet.kcal}"/></p>
	<p><b>protein:</b> <c:out value="${diet.protein}"/></p>
	<p><b>carb:</b> <c:out value="${diet.carb}"/></p>
	<p><b>fat:</b> <c:out value="${diet.fat}"/></p>
			
	<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/{trainingId}/diets/{dietId}/edit" var="dietUpdateurl" >
	<spring:param name="trainingId" value="${training.id}"/>
	<spring:param name="dietId" value="${diet.id}"/>
	</spring:url>

	<h3><a href="${fn:escapeXml(dietUpdateurl)}">Update Diet</a></h3>

</yogogym:layout>
