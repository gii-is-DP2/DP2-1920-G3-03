<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="challenges">
    <h2>Retos</h2>

    <table id="challengesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 100px;">Descripción</th>
            <th style="width: 100px;">Recompensa</th>
            <th style="width: 100px">Puntos</th>
            <th style="width: 100px">Comienza</th>
            <th style="width: 100px">Finaliza</th>
            <th style="width: 100px">Ejercicio</th>
            <th style="width: 100px">Repeticiones</th>
            <th style="width: 100px">Peso</th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${challenges}" var="challenge">
            <tr>
                <!-- <td>
                    <spring:url value="/owners/{ownerId}" var="ownerUrl">
                        <spring:param name="ownerId" value="${owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${owner.firstName} ${owner.lastName}"/></a>
                </td> -->
                <td>
                    <c:out value="${challenge.description}"/>
                </td>
                <td>
                    <c:out value="${challenge.reward}"/>
                </td>
                <td>
                    <c:out value="${challenge.points}"/>
                </td>
                <td>
                    <c:out value="${challenge.start}"/>
                </td>
                <td>
                    <c:out value="${challenge.end}"/>
                </td>
                <td>
                    <spring:url value="/exercises/{exerciseId}" var="exerciseUrl">
                        <spring:param name="exerciseId" value="${challenge.exercise.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(exerciseUrl)}"><c:out value="${challenge.exercise.name}"/></a>
                </td>
                <td>
                    <c:out value="${challenge.reps}"/>
                </td>
                <td>
                    <c:out value="${challenge.weight}"/>
                </td>
                         
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
    
     <a class="btn btn-default" href='<spring:url value="/challenges/new" htmlEscape="true"/>'>Crear Reto</a>
    
    
</petclinic:layout>