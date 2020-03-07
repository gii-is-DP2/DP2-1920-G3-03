<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="exercises">

    <h2>Exercise Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${exercise.name}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${exercise.description}"/></td>
        </tr>
        <tr>
            <th>Kcal</th>
            <td><c:out value="${exercise.kcal}"/></td>
        </tr>
        <tr>
            <th>Intensity</th>
            <td><c:out value="${exercise.intensity}"/></td>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>
    <h2>Machine</h2>

    <table class="table table-striped">
            <tr>
	           <td>
                    <spring:url value="/mainMenu/machines/{machineId}" var="machineUrl">
                        <spring:param name="machineId" value="${exercise.machine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(machineUrl)}"><c:out value="${exercise.machine.name}"/></a>
                </td>
	        </tr>
    </table>

</petclinic:layout>
