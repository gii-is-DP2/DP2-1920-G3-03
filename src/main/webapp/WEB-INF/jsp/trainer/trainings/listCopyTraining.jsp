<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">
    <h2>Trainings to copy</h2>
<c:if test="${!notHaveTrainingsPublic}">
    <table id="trainingsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Author</th>
            <th>Diet Name</th>
            <th>Diet Description</th>
            <th>Routine Name</th>
            <th>Routine Description</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${notHaveTrainingsPublic}">
	<b>We haven't public clients with trainings</b>
</c:if>
</yogogym:layout>
