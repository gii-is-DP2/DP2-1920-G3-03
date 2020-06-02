<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="diets">
    <jsp:body>

    <c:choose>
	    <c:when test="${diet['new']}">
            <h2>New Diet for <c:out value="${client.firstName} ${client.lastName}"/></h2>

            <h3>Diet Data</h3>

            <form:form modelAttribute="diet" class="form-horizontal">
            	
            	<div class="form-group has-feedback">
	                <fmt:message var="name" key="name"/>
	                <fmt:message var="description" key="description"/>
	                <fmt:message var="type" key="type"/>
	
	                <yogogym:inputField label="${name}" name="name"/>
	                <yogogym:inputField label="${description}" name="description"/>
	                <yogogym:selectField label="${type}" name="type" names="${dietTypes}" size="1"/>
		
					<input type="hidden" name="carb" value="0"/>
					<input type="hidden" name="fat" value="0"/>
					<input type="hidden" name="kcal" value="0"/>
					<input type="hidden" name="protein" value="0"/>
						
	                <div class="form-group">
	                    <div class="col-sm-offset-2 col-sm-10">
	                        <button class="btn btn-default" type="submit"><fmt:message key="saveDiet"/></button>
	                    </div>
	                </div>
                </div>
            </form:form>
	    </c:when>
	    <c:otherwise>
	    
	        <h2>Editing Diet for <c:out value="${client.firstName} ${client.lastName}"/></h2>
            <form:form modelAttribute="diet" id="dietForm" class="form-horizontal">
            
            <div class="form-group has-feedback">
                <fmt:message var="name" key="name"/>
                <fmt:message var="description" key="description"/>
                <fmt:message var="type" key="type"/>
                <fmt:message var="kcal" key="kcal"/>
                <fmt:message var="protein" key="protein"/>
                <fmt:message var="carb" key="carb"/>
                <fmt:message var="fat" key="fat"/>

            	<yogogym:inputField label="${name}" name="name"/>
                <yogogym:inputField label="${description}" name="description"/>
                <yogogym:selectField label="${type}" name="type" names="${dietTypes}" size="1"/>
                <yogogym:inputField label="${kcal}" name="kcal"/>
                <yogogym:inputField label="${protein}" name="protein"/>
                <yogogym:inputField label="${carb}" name="carb"/>
                <yogogym:inputField label="${fat}" name="fat"/>
				
				
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Save Diet</button>
                    </div>
                </div>
                
            </div>    
            </form:form>

	    </c:otherwise>
	</c:choose>



    </jsp:body>
</yogogym:layout>
