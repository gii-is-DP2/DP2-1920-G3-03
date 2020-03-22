<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="challenges">
    <h2>Challenges</h2>

    <table id="challengesTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Name</th>
            <th>Points</th>
            <th>Reward</th>
            <th>Start Date</th>
            <th>Finish Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${inscriptions}" var="inscription">
            <tr>
                <td>
                    <spring:url value="/client/${clientUsername}/challenges/mine/{challengeId}" var="showUrl">
        				<spring:param name="challengeId" value="${inscription.challenge.id}"/>
   					</spring:url>
    				<a href="${fn:escapeXml(showUrl)}"><c:out value="${inscription.challenge.name}"/></a>
                </td>
                <td>
                    <c:out value="${inscription.challenge.points}"/>
                </td>
                <td>
                    <c:out value="${inscription.challenge.reward}"/>
                </td>
                <td>
                    <c:out value="${inscription.challenge.initialDate}"/>
                </td>
                <td>
                    <c:out value="${inscription.challenge.endDate}"/>
                </td>
                <td>
                    <c:out value="${inscription.status}"/>
                </td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
</petclinic:layout>