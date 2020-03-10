<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="exercisess">

    <h2>Exercise</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${exercise.name}"/></b></td>
        </tr>
        <tr>
            <th>Descripción</th>
            <td><c:out value="${exercise.description}"/></td>
        </tr>
        <tr>
            <th>Kcalorías</th>
            <td><c:out value="${exercise.kcal}"/></td>
        </tr>
        <tr>
            <th>Intensidad</th>
            <td><c:out value="${exercise.intensity}"/></td>
        </tr>
        <tr>
            <th>Maquina</th>
            <td><c:out value="${exercise.machine.name}"/></td>
        </tr>
        <tr>
            <th>Localización</th>
            <td><c:out value="${exercise.machine.location}"/></td>
        </tr>
    </table>


</petclinic:layout>
