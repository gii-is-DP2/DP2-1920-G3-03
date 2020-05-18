<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="messages">
    <h2>All Message</h2>

	<c:forEach var="message" items="${messages}">
	<div>
		
		<c:out value="${message.content}"/>
		
		<ul>
			<c:forEach var="answer" items="${message.answers}">
				<li><c:out value="${answer.content}"/></li>
			</c:forEach>
		</ul>
		
	</div>
	<br>
	</c:forEach>
    
</yogogym:layout>
