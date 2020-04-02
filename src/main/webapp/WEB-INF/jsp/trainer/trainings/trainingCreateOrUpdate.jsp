<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="trainings">
	<jsp:attribute name="customScript">
        <script>
            $(function datePicker() {
            	if(${training['new']}){
            		$("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
            	}
            	if(${training['new']||(!training['new']&&endDateAux>=actualDate)}){
            		$("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            	}
            });
            $(document).ready(function createSelect() {
            	if (window.location.href.indexOf("trainer") > -1) {
		    		document.getElementById("clientSelect").style.display = "none";
		    		document.getElementById("bothSelect").innerHTML = "Yes, my client can edit his/her the training.";
		    	}
        		else{
		    		document.getElementById("trainerSelect").style.display = "none";
		    		document.getElementById("bothSelect").innerHTML = "Yes, my trainer can edit my training.";
		    	}
            	if(${training['new']}){
            		if (window.location.href.indexOf("trainer") > -1) {
            			document.getElementById("editingPermission").selectedIndex = "0";
    		    	}
            		else{
            			document.getElementById("editingPermission").selectedIndex = "1";
    		    	}
            	}
            	else{
            		if(${training['editingPermission']=='TRAINER'}){
	            		document.getElementById("editingPermission").selectedIndex = "0";
	            	}
	            	else if(${training['editingPermission']=='CLIENT'}){
	            		document.getElementById("editingPermission").selectedIndex = "1";
	            	}
	            	else{
	            		document.getElementById("editingPermission").selectedIndex = "2";
	            	}
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
				<yogogym:inputField label="Initial Date" name="initialDate" readonly="${!training['new']}" pattern="^\d{4}\/\d{2}\/\d{2}$" placeholder="yyyy/MM/dd"/>
	            <yogogym:inputField label="End Date" name="endDate" readonly="${!(training['new']||(!training['new']&&endDateAux>=actualDate))}" pattern="^\d{4}\/\d{2}\/\d{2}$" placeholder="yyyy/MM/dd"/>
	            <label class="col-sm-2 control-label">Share Editing Permission</label>
	            <div class="col-sm-4">
				    <select class="form-control" id="editingPermission" name="editingPermission">
				    	<option id="trainerSelect" value="TRAINER">No, my client cannot edit his/her training.</option>
				    	<option id="clientSelect" value="CLIENT">No, my trainer cannot edit my training.</option>
		            	<option id="bothSelect" value="BOTH"></option>
		            </select>
	            </div>
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
