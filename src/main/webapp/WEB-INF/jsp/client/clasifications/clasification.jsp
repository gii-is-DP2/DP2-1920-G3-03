<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="challenges">
    <h2>Challenges completed</h2>

	<c:if test="${hasChallenge}">
    	<table id="challengesTable" class="table table-striped">
        	<thead>
        	<tr>
        		<th>Name</th>
        		<th>Description</th>
            	<th>Points</th>
        	</tr>
        	</thead>
        	<tbody>
        	<c:forEach items="${challenges}" var="challenge">
            	<tr>
                	<td>
                    	<c:out value="${challenge.name}"/>
                	</td>
                	<td>
                    	<c:out value="${challenge.description}"/>
                	</td>
                	<td>
                    	<c:out value="${challenge.points}"/>
                	</td>
            	</tr>
        	</c:forEach>
        	</tbody>
    	</table>
	</c:if>
	<c:if test="${!hasChallenge}">
		You haven't completed challenges
	</c:if>
    
</petclinic:layout>