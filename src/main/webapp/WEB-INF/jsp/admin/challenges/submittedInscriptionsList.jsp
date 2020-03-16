<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="inscriptions">
    <h2>Submitted Inscriptions</h2>

    <table id="challengesTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Challenge</th>
            <th>End Date</th>
            <th>Client</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
	        <c:forEach items="${inscriptions}" var="inscription">
	                <td>
	                    <spring:url value="/admin/inscriptions/submitted/{inscriptionId}" var="showUrl">
	        				<spring:param name="inscriptionId" value="${inscription.id}"/>
	   					</spring:url>
	    				<a href="${fn:escapeXml(showUrl)}"><c:out value="${inscription.challenge.name}"/></a>
	                </td>
	                <td>
	                    <c:out value="${inscription.challenge.endDate}"/>
	                </td>
	        </c:forEach>
	         <c:forEach items="${clients}" var="client">
	                <td>
	                    <c:out value="${client.firstName}"/>
	                </td>
	        </c:forEach>
        </tr>
        
        </tbody>
    </table>
    
    
</petclinic:layout>