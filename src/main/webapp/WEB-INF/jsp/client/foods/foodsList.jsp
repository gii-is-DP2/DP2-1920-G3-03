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
				<th>Food Type</th>
				<th></th>
	        </tr>
	        </thead>

	<c:forEach var="foods" items="${foods}">				
		<tr>
		<spring:url value="/client/${clientUsername}/trainings/${training.id}/diets/${diet.id}/food/${foods.id}" var="foodAddUrl" />
			<td> <a href="${fn:escapeXml(foodAddUrl)}"><c:out value="${foods.name}"/></a></td>	
			<td><c:out value="${foods.foodType}"/></td>
			
		</tr>	 	
	</c:forEach>
	 </table>
</yogogym:layout>