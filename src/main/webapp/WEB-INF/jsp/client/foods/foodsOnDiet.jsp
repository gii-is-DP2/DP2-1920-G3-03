<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>
<yogogym:layout pageName="foodsDetails">
  <h2><c:out value="${diet.name}"/> foods </h2>
  
  	<table class="table table-striped">
			<thead>
	        <tr>
				
				<th>Name</th>
	            <th>Kcals</th>
	            <th>Proteins</th>
				<th>Carbs</th>
	            <th>Fats</th>
				<th>Weight</th>
				<th>Food Type</th>
				<th></th>
				
	        </tr>
	        </thead>
	        <c:forEach var="foods" items="${foods}">	
			<tr>
				<td><c:out value="${foods.name}"/></td>	
				<td><c:out value="${foods.kcal}"/></td>	
				<td><c:out value="${foods.protein}"/></td>	
				<td><c:out value="${foods.fat}"/></td>	
				<td><c:out value="${foods.carb}"/></td>	
				<td><c:out value="${foods.weight}"/></td>	
				<td><c:out value="${foods.foodType}"/></td>	
				
				<td><spring:url value="/client/${clientUsername}/trainings/${trainingId}/diets/${diet.id}/food/${foods.id}/deleteFood"
				var="deleteOneFood">
				</spring:url>
		<a href="${fn:escapeXml(deleteOneFood)}" class="btn btn-default">Delete this food</a>
				
				</td>
			</tr>	
		</c:forEach>
		</table>
		
		<spring:url value="/client/${clientUsername}/trainings/${trainingId}/diets/${diet.id}/showFoods/delete"
			var="deleteFood">
		</spring:url>
		<a href="${fn:escapeXml(deleteFood)}" class="btn btn-default">Delete All Foods</a>
  </yogogym:layout>