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
            
            <%-- 
            	<th>Description</th>
	            <th>Exercise</th>
	            <th>Repetitions</th>
	            <th>Weight</th>
	            <th>Reward</th>
            --%>
            
            
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${challenges}" var="challenge">
            <tr>
                <td>
                    <c:out value="${challenge.name}"/>
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
                <%-- 
	                <td>
	                    <c:out value="${challenge.description}"/>
	                </td>
	                <td>
	                    <c:out value="${challenge.reward}"/>
	                </td>                
	                <td>
	                    <spring:url value="/mainMenu/exercises/{exerciseId}" var="exerciseUrl">
	                        <spring:param name="exerciseId" value="${challenge.exercise.id}"/>
	                    </spring:url>
	                    <a href="${fn:escapeXml(exerciseUrl)}"><c:out value="${challenge.exercise.name}"/></a>
	                </td>
	                <td>
	                    <c:out value="${challenge.reps}"/>
	                </td>
	                <td>
	                    <c:out value="${challenge.weight}"/>
	                </td>
                --%>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
     <a class="btn btn-default" href='<spring:url value="challenges/new" htmlEscape="true"/>'>Create Challenge</a>
    
    
</petclinic:layout>