<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
	<h2>Trainer: Client Details</h2>

	<table class="table table-striped">
   		<tr>
            <th>First Name</th>
            <td><b><c:out value="${client.firstName}"/></b></td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td><c:out value="${client.lastName}"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${client.email}"/></td>
        </tr>
    </table>
    
    <h2><c:out value="${client.firstName}"/> Routines</h2>
    <table class="table table-striped">
    	<c:forEach var="routine" items="${client.routines}">
	   		 <tr>
	           <td>
                    <spring:url value="/trainer/${trainerUsername}/clients/${clientId}/routines/{routineId}" var="routineUrl">
                        <spring:param name="routineId" value="${routine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(routineUrl)}"><c:out value="${routine.name}"/></a>
                </td>
	        </tr>
        </c:forEach>
    </table>
    
</yogogym:layout>
