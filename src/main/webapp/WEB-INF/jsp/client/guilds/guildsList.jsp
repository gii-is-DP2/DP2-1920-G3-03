<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="guilds">

    <h2>All Guilds</h2>
		<table class="table table-stripped">
		<thead>
		<tr>
		<th>Logo</th>
		<th>Name</th>
		<th>Description</th>
		</tr>
		</thead>
		<c:forEach var="guild" items="${allGuilds}">
		<tr>	
			<td>
			 <spring:url value="/client/${clientUsername}/guilds/{guildId}" var="showUrl">
        				<spring:param name="guildId" value="${guild.id}"/>
   			</spring:url>
			<img src="<c:url value="${guild.logo}"/>" width ="150" height="100"/>
			</td>
			<td><a href="${fn:escapeXml(showUrl)}"><c:out value="${guild.name}"/></a></td>
			<td><c:out value="${guild.description}"/></td>
			
		</tr>
		</c:forEach>
		</table>
		
<c:choose>
<c:when test="${empty client.guild.id}">
<h2>You don't like any Guild? Create yours!

</h2>
<a href="guilds/create" class="btn btn-default">Create a Guild</a>
</c:when>
<c:otherwise>
<a href="guilds/${client.guild.id}" class="btn btn-default">See your Guild</a>
</c:otherwise>
</c:choose>

</yogogym:layout>
