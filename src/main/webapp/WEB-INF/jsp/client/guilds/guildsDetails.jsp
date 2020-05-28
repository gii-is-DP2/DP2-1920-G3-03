<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="guildsDetails">
	<h1 style="color:#F2502D;font-size:xx-large" align="left"><c:out value="${guild.name}"/></h1>
	<p></p>
	<p><img src="<c:url value="${guild.logo}"/>" width ="380" height="300"/></p>
	
	<p><b>Description:</b> <c:out value="${guild.description}"/></p>
	<p><b>Creator:</b> <c:out value="${guild.creator}"/></p>
	<p><b>Total Points:</b> <c:out value="${points}"/></p>
	<p><b>Total Number of Users:</b> <c:out value="${clients}"/></p>
	<c:if test="${guild.creator == clientUsername}">
		<spring:url value="/client/${clientUsername}/guilds/${guild.id}/edit"
			var="editUrl">
		</spring:url>
		<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit</a>
		
		<spring:url value="/client/${clientUsername}/guilds/${guild.id}/delete"
			var="deleteUrl">
		</spring:url>
		<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>
	</c:if>	
		
	<c:if test="${empty client.guild}">
		<spring:url value="/client/${clientUsername}/guilds/${guild.id}/join"
			var="joinUrl">
		</spring:url>
		<a href="${fn:escapeXml(joinUrl)}" class="btn btn-default">Join the Guild</a>
	</c:if>
	<c:if test="${(client.guild.id == guild.id) && (guild.creator != client.user.username)}">
		<spring:url value="/client/${clientUsername}/guilds/${guild.id}/leave"
			var="leaveUrl">
		</spring:url>
		<a href="${fn:escapeXml(leaveUrl)}" class="btn btn-default">Leave the Guild</a>
	</c:if>
	
	<c:if test="${client.guild.id==guild.id}">
		<spring:url value="/client/${clientUsername}/guilds/${guild.id}/forums/${forumId}"
			var="forumUrl">
		</spring:url>
		<a href="${fn:escapeXml(forumUrl)}" class="btn btn-default">Forum</a>
	</c:if>
	
</yogogym:layout>
