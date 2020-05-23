<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">

    <h2>Client's diets</h2>
		
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
					<td><a style="color:grey">Only trainer can edit</a></td>		

				</c:when>
				<c:otherwise>
				 	<td><c:out value="${training.diet.name}"/></td>
		            <td><c:out value="${training.diet.description}"/></td>
		            <td><c:out value="${training.diet.type}"/></td>
		            <td><c:out value="${training.diet.kcal}"/></td>
		            <td><c:out value="${training.diet.protein}"/></td>
					<td><c:out value="${training.diet.carb}"/></td>
		            <td><c:out value="${training.diet.fat}"/></td>
					<td><a style="color:grey">Only trainer can edit</a></td>		

				</c:otherwise>
		        </c:choose>

			</tr>	
		</c:forEach>
		</table>
	<br>		
    <br>	

</yogogym:layout>
