<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">
    <h2>Trainings to copy</h2>
<c:if test="${!notHaveTrainingsPublic}">
<sec:authorize access="isAuthenticated()">
	<c:set var="trainerUsername">
		<sec:authentication property="principal.username"/>
	</c:set>
</sec:authorize>
    <table id="trainingsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Author</th>
            <th>Diet Name</th>
            <th>Diet Description</th>
            <th>Routine Name</th>
            <th>Routine Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trainings}" var="training">
            <tr>
                <td>
                    <c:out value="${training.name}"/>
                </td>
                <td>
                    <c:out value="${training.author}"/>
                </td>
                <td>
                    <c:out value="${training.diet.name}"/>
                </td>
                <td>
                    <c:out value="${training.diet.description}"/>
                </td>                
                
                <td>
                    <c:forEach items="${training.routines}" var="routine">
                    	<c:out value="${routine.name}"/>
                    	<br>
                    </c:forEach>
                </td>
                <td>	 
                	<c:forEach items="${training.routines}" var="routine">
                    	<c:out value="${routine.description}"/>
                    	<br>
                    </c:forEach>
                </td>
                <td>
                	<form:form modelAttribute="training" class="form-horizontal" id="trainingForm">
                		<input type="hidden" name="trainerUsername" value="${trainerUsername}">
                		<input type="hidden" name="trainingIdToCopy" value="${training.id}">
                		<button class="btn btn-default" type="submit">Copy Training</button>
                	</form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${notHaveTrainingsPublic}">
	<b>We haven't public clients with trainings</b>
</c:if>
</yogogym:layout>
