<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="routines">
	
	<jsp:attribute name="customScript">
        <script>
            $(function () {
            	if(${training['new']}){
            		$("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
            	}
            	if(${training.endDate>=actualDate||training.endDate==null||!hasErrors['endDate'].isEmpty()}){
            		$("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            	}
               
            });
        </script>
    </jsp:attribute>
	<jsp:body>
		<c:choose>
	            <c:when test="${training['new']}">
	                <h2>New Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>
	            </c:when>
	            <c:otherwise>
	                <h2>Editing Training for <c:out value="${client.firstName} ${client.lastName}"/></h2>
	            </c:otherwise>
	        </c:choose>
		
		<h3>General information</h3>
		<form:form modelAttribute="training" class="form-horizontal" id="trainingForm">
			<div class="form-group has-feedback">
				<input type="hidden" name="id" id="id" class="form-control" value="${training.id}"/>
				<input type="hidden" name="client" value="${client.nif}"/>
				<yogogym:inputField label="Name" name="name"/>
				<yogogym:inputField label="Initial Date" name="initialDate" readonly="${!training['new']}"/>
	            <yogogym:inputField label="End Date" name="endDate" readonly="${training.endDate<actualDate}"/>
	            
            </div>
			<div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">
	                <c:choose>
	                    <c:when test="${training['new']}">
	                        <button class="btn btn-default" type="submit">Add Training</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class="btn btn-default" type="submit">Update Training</button>
	                    </c:otherwise>
	                </c:choose>
	            </div>
	        </div>
		</form:form>
	</jsp:body>
    
</yogogym:layout>
