<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <h2>Diets</h2>

    <table id="dietsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Kcal</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${diets}" var="diet">
            <tr>
                <td>
                    <spring:url value="/mainMenu/diets/{dietId}" var="dietUrl">
                        <spring:param name="dietId" value="${diet.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(dietUrl)}"><c:out value="${diet.name}"/></a>
                </td>
                <td>
                    <c:out value="${diet.kcal}"/>
                </td>
            </tr>
        </c:forEach>
            
        </tbody>
    </table>
    <a href="diets/new" class="btn btn-default"><fmt:message key="newDiet"/></a>

</yogogym:layout>
