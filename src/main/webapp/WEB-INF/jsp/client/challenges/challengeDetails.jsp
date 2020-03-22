<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<yogogym:layout pageName="challenges">

	<h2>Challenge</h2>

	<table id="challengesTable" class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${challenge.name}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${challenge.description}"/></td>
        </tr>
        <tr>
            <th>Points</th>
            <td><c:out value="${challenge.points}"/></td>
        </tr>
        <tr>
            <th>Stard Date</th>
            <td><c:out value="${challenge.initialDate}"/></td>
        </tr>
        <tr>
            <th>End Date</th>
            <td><c:out value="${challenge.endDate}"/></td>
        </tr>
        <tr>
            <th>Reward</th>
            <td><c:out value="${challenge.reward}"/></td>
        </tr>
        <tr>
            <th>Repetitions</th>
            <td><c:out value="${challenge.reps}"/></td>
        </tr>
        <tr>
            <th>Weight</th>
            <td><c:out value="${challenge.weight}"/></td>
        </tr>
        <tr>
            <th>Exercise</th>
            <td>
            	<spring:url value="/mainMenu/exercises/{exerciseId}" var="exerciseUrl">
					<spring:param name="exerciseId" value="${challenge.exercise.id}" />
				</spring:url> 
				<a href="${fn:escapeXml(exerciseUrl)}"><c:out value="${challenge.exercise.name}" /></a>
            </td>
        </tr>
    </table>
    
    <spring:url value="/client/${clientUsername}/challenges/${challenge.id}/inscription/create" var="createInscriptionUrl"></spring:url>
	<a href="${fn:escapeXml(createInscriptionUrl)}" class="btn btn-default">Inscribe me!</a>

</yogogym:layout>
