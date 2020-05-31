<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">


	<h3>Client params: </h3>
	<table class="table table-striped">
			<thead>
	        <tr>
				<th>Weight</th>
	        	<th>Height</th>
	            <th>Age</th>
	            <th>Fat percentage</th>
	            
	        </tr>
	        </thead>
			<tr>
				<td><c:out value="${client.weight}"/></td>	
				<td><c:out value="${client.height}"/></td>	
				<td><c:out value="${client.age}"/></td>	
				<td><c:out value="${client.fatPercentage}"/></td>	
				
			</tr>	
		</table>

	<h3>Diet: </h3>
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
	            <th>Foods</th>
				<th>Edit</th>
				<th>Add Food</th>
	        </tr>
	        </thead>
			<tr>
				<td><c:out value="${training.name}"/></td>	
				 	<td><c:out value="${diet.name}"/></td>
		            <td><c:out value="${diet.description}"/></td>
		            <td><c:out value="${diet.type}"/></td>
		            <td><c:out value="${diet.kcal}"/></td>
		            <td><c:out value="${diet.protein}"/></td>
					<td><c:out value="${diet.carb}"/></td>
		            <td><c:out value="${diet.fat}"/></td>
		            <spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/${diet.id}/showFoods" var="showFoods" />
		            <td> <a href="${fn:escapeXml(showFoods)}">Show Foods Added</a>	</td>	
					
					<c:choose>
						<c:when test="${training.endDate < actualDate}">	
							<td><a style="color:grey">Edit</a></td>		
						</c:when>
						<c:otherwise>
							<spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/create" var="dietAddUrl" />
							<td> <a href="${fn:escapeXml(dietAddUrl)}">Edit</a>	</td>	
								<spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/${diet.id}/foods" var="dietAddFood" />	
							<td> <a href="${fn:escapeXml(dietAddFood)}">Add Food</a></td>	
						</c:otherwise>
					</c:choose>
			</tr>	
		</table>

</yogogym:layout>
