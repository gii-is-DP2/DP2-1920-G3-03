<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <h2>Diet Details</h2>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${diet.name}"/></b></td>
        </tr>
        <tr>
            <th>Kcal</th>
            <td><c:out value="${diet.kcal}"/></td>
        </tr>
        <tr>
            <th>Carbs</th>
            <td><c:out value="${diet.carb}"/></td>
        </tr>   
        <tr>
            <th>Protein</th>
            <td><c:out value="${diet.protein}"/></td>
        </tr>   
        <tr>
            <th>Fat</th>
            <td><c:out value="${diet.fat}"/></td>
        </tr>           
    </table>
    <spring:url value="{dietId}/update" var="updateUrl">
        <spring:param name="dietId" value="${diet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(updateUrl)}" class="btn btn-default"><fmt:message key="updateDiet"/></a>

</yogogym:layout>
