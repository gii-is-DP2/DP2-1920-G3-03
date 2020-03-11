<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="equipments">
    <h2>Equipments</h2>

    <table id="equipmentTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Location</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${equipments}" var="equipment">
            <tr>
                <td>
                    <spring:url value="/mainMenu/equipments/{equipmentId}" var="equipmentUrl">
                        <spring:param name="equipmentId" value="${equipment.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(equipmentUrl)}"><c:out value="${equipment.name}"/></a>
                </td>
                <td>
                    <c:out value="${equipment.location}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</yogogym:layout>
