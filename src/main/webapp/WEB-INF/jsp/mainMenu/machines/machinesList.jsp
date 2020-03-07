<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="machines">
    <h2>Machines</h2>

    <table id="machinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Location</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${machines}" var="machine">
            <tr>
                <td>
                    <spring:url value="/mainMenu/machines/{machineId}" var="machineUrl">
                        <spring:param name="machineId" value="${machine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(machineUrl)}"><c:out value="${machine.name}"/></a>
                </td>
                <td>
                    <c:out value="${machine.location}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</yogogym:layout>
