<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="clients">
    <h2>My Clients</h2>

    <table id="exercisesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${trainer.clients}" var="client">
            <tr>
                <td>
                    <spring:url value="/trainer/${trainerUsername}/clients/{clientId}" var="clientUrl">
                        <spring:param name="clientId" value="${client.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${client.email}"/>
                </td>                                  
            </tr>
        </c:forEach>
        </tbody>
    </table>
</yogogym:layout>
