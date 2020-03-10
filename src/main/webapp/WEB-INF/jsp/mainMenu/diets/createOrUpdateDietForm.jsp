<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="diets">
   
    <jsp:body>
        <h2><c:if test="${diet['new']}"><fmt:message key="newDiet"/> </c:if>
        <h2><c:if test="${!diet['new']}"><fmt:message key="updateDiet"/> </c:if>

        <form:form modelAttribute="diet" class="form-horizontal">
            <div class="form-group has-feedback">
            		<fmt:message var="type" key="type"/>
                    <fmt:message var="kcal" key="kcal"/>
                    <fmt:message var="carb" key="carb"/>
                    <fmt:message var="protein" key="protein"/>
                    <fmt:message var="fat" key="fat"/>
                <petclinic:inputField label="${type}" name="type"/>
                <petclinic:inputField label="${kcal}" name="kcal"/>
                <petclinic:inputField label="${carb}" name="carb"/>
                <petclinic:inputField label="${protein}" name="protein"/>
                <petclinic:inputField label="${fat}" name="fat"/>

            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit"><fmt:message key="saveDiet"/></button>
                </div>
            </div>
        </form:form>

        <br/>
   
    </jsp:body>

</petclinic:layout>
