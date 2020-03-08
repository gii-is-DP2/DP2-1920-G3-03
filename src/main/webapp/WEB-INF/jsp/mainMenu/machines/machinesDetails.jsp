<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="machines">
    <h2>Machine Details</h2>

    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${machine.name}"/></b></td>
        </tr>
        <tr>
            <th>Location</th>
            <td><c:out value="${machine.location}"/></td>
        </tr>        
    </table>

</yogogym:layout>
