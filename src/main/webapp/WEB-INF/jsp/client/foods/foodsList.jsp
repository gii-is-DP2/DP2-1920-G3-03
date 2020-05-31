<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="foodsPage">
  <h2>All Foods</h2>
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
				<th><th>
	        </tr>
	        </thead>

	<c:forEach var="foods" items="${foods}">				
		<tr>
			<td> <c:out value="${foods.name}"/></td>	
			<td><c:out value="${foods.kcal}"/></td>	
				<td><c:out value="${foods.protein}"/></td>	
				<td><c:out value="${foods.fat}"/></td>	
				<td><c:out value="${foods.carb}"/></td>	
				<td><c:out value="${foods.weight}"/></td>	
				<td><c:out value="${foods.foodType}"/></td>	
			<td><spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/${diet.id}/food/${foods.id}/addFood" var="addFood" />
				<td> <a href="${fn:escapeXml(addFood)}">Add Food to your diet</a></td></td>
			
		</tr>	 	
	</c:forEach>
	 </table>
</yogogym:layout>