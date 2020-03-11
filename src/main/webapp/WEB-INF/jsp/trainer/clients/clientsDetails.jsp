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
        <tr>
            <th>Height</th>
            <td><c:out value="${client.height}"/></td>
        </tr>
        <tr>
            <th>Weight</th>
            <td><c:out value="${client.weight}"/></td>
        </tr>
    </table>
    
    <h2>Trainings</h2>
    <table class="table table-striped">
    	<c:forEach var="training" items="${client.trainings}">
	   		 <tr>
	           <td>
                    <spring:url value="/trainer/${trainerUsername}/clients/${clientId}/trainings/{trainingId}" var="trainingUrl">
                        <spring:param name="trainingId" value="${training.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(trainingUrl)}"><c:out value="${training.name}"/></a>
                </td>
	        </tr>
        </c:forEach>
    </table>
    
    <h2>Guild</h2>
    <table class="table table-striped">
    	
	   		 <tr>
	           <td>
                    <spring:url value="/trainer/${trainerUsername}/clients/${clientId}/guilds/{guildId}" var="guildUrl">
                        <spring:param name="guildId" value="${client.guild.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(guildUrl)}"><c:out value="${client.guild.name}"/></a>
                </td>
                <td>
                    <c:out value="${client.guild.description}"/>
                </td>
	        </tr>
     
    </table>
    
    <h2>Inscription</h2>
    <table class="table table-striped">
    	<c:forEach var="inscription" items="${client.inscriptions}">
    		<spring:url value="/trainer/${trainerUsername}/clients/${clientId}/challenges/{challengeId}" var="challengeUrl">
            	<spring:param name="challengeId" value="${inscription.challenge.id}"/>
            </spring:url>
    		<h3><a href="${fn:escapeXml(challengeUrl)}"><c:out value="${inscription.challenge.name}"/></a></h3>
	   		 <tr>
	           <td>                    
                    Status: <c:out value="${inscription.status}"/>
                </td>
	        </tr>
     	</c:forEach>
    </table>
    
</yogogym:layout>
