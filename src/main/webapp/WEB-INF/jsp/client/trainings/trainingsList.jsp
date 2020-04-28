<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
	<h2>My Trainings</h2>
    
    <h2>Trainings</h2>
    <table class="table table-striped">
    	<thead>
        <tr>
        	<th>Name</th>
            <th>Initial Date</th>
            <th>End Date</th>
            <th>Editable</th>
        </tr>
        </thead>
    	<c:forEach var="training" items="${trainings}">
	   		 <tr>
	           <td>
                    <spring:url value="/client/${clientUsername}/trainings/{trainingId}" var="trainingUrl">
                        <spring:param name="trainingId" value="${training.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(trainingUrl)}"><c:out value="${training.name}"/></a>
                </td>
                
                <td>
                    <c:out value="${training.initialDate}"/>
                </td>
                
                <td>
                   <c:out value="${training.endDate}"/>
                </td>
                
                <td>
                   <c:out value="${training.editingPermission}"/>
                </td>
                
	        </tr>
        </c:forEach>
    </table>
    
    <spring:url value="/client/${clientUsername}/trainings/create" var="trainingAddUrl" />
	<a href="${fn:escapeXml(trainingAddUrl)}">Add Training</a>
        
</yogogym:layout>
