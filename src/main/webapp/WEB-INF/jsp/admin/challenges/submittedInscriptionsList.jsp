<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="inscriptions">
    <h2>Submitted Inscriptions</h2><br>

	<c:forEach items="${clients}" var="client">
	
		<h3><c:out value="Client: ${client.firstName} ${client.lastName}"></c:out></h3>
		
		<table id="inscriptionsTable" class="table table-striped">
	        <thead>
		        <tr>
		        	<th>Challenge</th>
		            <th>Initial Date</th>
		            <th>End Date</th>
		        </tr>
		    </thead>
	        <tbody>
	        	<c:forEach items="${client.inscriptions}" var="inscription">
	        		<tr>
		                <td>
		                    <spring:url value="/admin/inscriptions/submitted/{inscriptionId}" var="showInscriptionUrl">
		        				<spring:param name="inscriptionId" value="${inscription.id}"/>
		   					</spring:url>
		    				<a href="${fn:escapeXml(showInscriptionUrl)}"><c:out value="${inscription.challenge.name}"/></a>
		                </td>
		                <td>
		                    <c:out value="${inscription.challenge.initialDate}"/>
		                </td>
		                <td>
		                    <c:out value="${inscription.challenge.endDate}"/>
		                </td>
	        		</tr>
	        	</c:forEach>
	        </tbody>
    	</table>
	
	</c:forEach>
    
    
    
</petclinic:layout>