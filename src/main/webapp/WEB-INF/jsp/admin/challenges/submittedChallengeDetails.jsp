<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<yogogym:layout pageName="challenges">

	<h2>Challenge</h2>

	<table id="challengesTable" class="table table-striped">
		<tr>
			<th>Name</th>
			<td><b><c:out value="${inscription.challenge.name}" /></b></td>
		</tr>
		<tr>
			<th>Description</th>
			<td><c:out value="${inscription.challenge.description}" /></td>
		</tr>
		<tr>
			<th>Points</th>
			<td><c:out value="${inscription.challenge.points}" /></td>
		</tr>
		<tr>
			<th>Stard Date</th>
			<td><c:out value="${inscription.challenge.initialDate}" /></td>
		</tr>
		<tr>
			<th>End Date</th>
			<td><c:out value="${inscription.challenge.endDate}" /></td>
		</tr>
		<tr>
			<th>Reward</th>
			<td><c:out value="${inscription.challenge.reward}" /></td>
		</tr>
		<tr>
			<th>Repetitions</th>
			<td><c:out value="${inscription.challenge.reps}" /></td>
		</tr>
		<tr>
			<th>Weight</th>
			<td><c:out value="${inscription.challenge.weight}" /></td>
		</tr>
		<tr>
			<th>Exercise</th>
			<td><spring:url value="/mainMenu/exercises/{exerciseId}"
					var="exerciseUrl">
					<spring:param name="exerciseId" value="${inscription.challenge.exercise.id}" />
				</spring:url> <a href="${fn:escapeXml(exerciseUrl)}"><c:out
						value="${inscription.challenge.exercise.name}" /></a></td>
		</tr>
	</table>

	<h2>Inscription</h2>

	<table id="inscriptionTable" class="table table-striped">
		<tr>
			<th>Evidence</th>
			<td><a href="${inscription.url}"><c:out value="${inscription.url}" /></a></td>
		</tr>
		<tr>
			<th>Client</th>
			<td><c:out value="${client.firstName} ${client.lastName}" /></td>
		</tr>
	</table>

	<form:form modelAttribute="inscription" class="form-horizontal"
		       action="/admin/challenges/submitted/${inscription.challenge.id}/inscription/${inscription.id}/evaluate">

		<input type="hidden" name="url" value="${inscription.url}">
		
		<select id="status" name="status" required="required">
			<option value="COMPLETED">Completed</option>
			<option value="FAILED">Failed</option>
		</select>
		
		<input type="submit" value="Evaluate!">

	</form:form>
</yogogym:layout>
