<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="foodsDetails">
  <h2><c:out value="${food.name}"/> details </h2>
  
  	<table class="table table-striped">
			<thead>
	        <tr>
				
	            <th>Kcals</th>
	            <th>Proteins</th>
				<th>Carbs</th>
	            <th>Fats</th>
				<th>Weight</th>
				<th>Food Type</th>
				<th></th>
				
	        </tr>
	        </thead>
			<tr>
				<td><c:out value="${food.kcal}"/></td>	
				<td><c:out value="${food.protein}"/></td>	
				<td><c:out value="${food.fat}"/></td>	
				<td><c:out value="${food.carb}"/></td>	
				<td><c:out value="${food.weight}"/></td>	
				<td><c:out value="${food.foodType}"/></td>	
				<spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/${diet.id}/food/${food.id}/addFood" var="addFood" />
				<td> <a href="${fn:escapeXml(addFood)}">Add Food to your diet</a></td>	
			</tr>	
		</table>
  </yogogym:layout>