<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">

    <h2>Client's diets</h2>
    
    <c:forEach var="client" items="${trainer.clients}">
    	
		<spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
			<spring:param name="clientId" value="${client.id}"/>
		</spring:url>
		<h3> Client: <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a> ( <c:out value ="${client.email}"/> )</h3>
		
		<table class="table table-striped">
			<thead>
	        <tr>
				<th>Training</th>
	        	<th>Diet</th>
	            <th>Description</th>
	            <th>Type</th>
	            <th>Kcals</th>
	            <th>Proteins</th>
				<th>Carbs</th>
	            <th>Fats</th>
				<th>Edit</th>
	        </tr>
	        </thead>

		<c:forEach var="training" items="${client.trainings}">				
				<tr>
				 	<td><c:out value="${training.name}"/></td>
					 
				<c:choose>
				<c:when test="${empty training.diet }">
					<td></td>
		            <td></td>
		            <td></td>
		            <td></td>
		            <td></td>
					<td></td>
		            <td></td>
					<c:choose>
						<c:when test="${training.endDate < actualDate}">	
							<td><a style="color:grey">Add Diet</a></td>		
						</c:when>
						<c:otherwise>
						<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/${training.id}/diets/create" var="dietAddUrl" />
							<td> <a href="${fn:escapeXml(dietAddUrl)}">Add Diet</a>	</td>							
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
				 	<td><c:out value="${training.diet.name}"/></td>
		            <td><c:out value="${training.diet.description}"/></td>
		            <td><c:out value="${training.diet.type}"/></td>
		            <td><c:out value="${training.diet.kcal}"/></td>
		            <td><c:out value="${training.diet.protein}"/></td>
					<td><c:out value="${training.diet.carb}"/></td>
		            <td><c:out value="${training.diet.fat}"/></td>
					
					<c:choose>
						<c:when test="${training.endDate < actualDate}">	
							<td><a style="color:grey">Edit</a></td>		
						</c:when>
						<c:otherwise>
							<spring:url value="/trainer/${trainerUsername}/clients/${client.id}/trainings/{trainingId}/diets/{dietId}/edit" var="dietUpdateurl" >
							<spring:param name="trainingId" value="${training.id}"/>
							<spring:param name="dietId" value="${training.diet.id}"/>
							</spring:url>
							<td><a href="${fn:escapeXml(dietUpdateurl)}">Edit</a></td>
												
						</c:otherwise>
					</c:choose>
				</c:otherwise>
		        </c:choose>

			</tr>	
		</c:forEach>
		</table>
	<br>		
    <br>	
    </c:forEach>

</yogogym:layout>
