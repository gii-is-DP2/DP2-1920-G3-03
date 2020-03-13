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
            <th>Start Date</th>
            <th>Finish Date</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${challenges}" var="challenge">
            <tr>
                <td>
                    <spring:url value="/admin/challenges/{challengeId}" var="showUrl">
        				<spring:param name="challengeId" value="${challenge.id}"/>
   					</spring:url>
    				<a href="${fn:escapeXml(showUrl)}"><c:out value="${challenge.name}"/></a>
                </td>
                <td>
                    <c:out value="${challenge.points}"/>
                </td>
                <td>
                    <c:out value="${challenge.initialDate}"/>
                </td>
                <td>
                    <c:out value="${challenge.endDate}"/>
                </td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
     <a class="btn btn-default" href='<spring:url value="challenges/new" htmlEscape="true"/>'>Create Challenge</a>
    
    
</petclinic:layout>