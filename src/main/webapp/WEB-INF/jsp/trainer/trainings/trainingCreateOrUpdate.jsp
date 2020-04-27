<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
            	if(${!training['new']}){
            		if(${training['editingPermission']!='BOTH'}){
            			document.getElementById("editingPermission").selectedIndex = "0";
            		}
            		else{
            			document.getElementById("editingPermission").selectedIndex = "1";
            		}
            	}
            });
        </script>
    </jsp:attribute>
	<jsp:body>
		<sec:authorize access="isAuthenticated()">
			<c:set var="principalUsername">
				<sec:authentication property="principal.username"/>
			</c:set>
		</sec:authorize>
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
				<input type="hidden" name="author" value="${training['new'] ? principalUsername : training.author}"/>
				<yogogym:inputField label="Name" name="name"/>
				<yogogym:inputField label="Initial Date" name="initialDate" readonly="${!training['new']}" pattern="^\d{4}\/\d{2}\/\d{2}$" placeholder="yyyy/MM/dd"/>
	            <yogogym:inputField label="End Date" name="endDate" readonly="${!(training['new']||(!training['new']&&endDateAux>=actualDate))}" pattern="^\d{4}\/\d{2}\/\d{2}$" placeholder="yyyy/MM/dd"/>
	            <div class="form-group">
		            <label class="col-sm-2 control-label">Editing Permission</label>
		            <div class="col-sm-10">
		            	<c:choose>
		            		<c:when test="${training.author!=null&&training.author!=principalUsername}">
		            			<select class="form-control" id="editingPermission" name="editingPermission" disabled>
							    	<option value="CLIENT">Only the client can manage the training.</option>
					            	<option value="BOTH">You and your client can manage the training.</option>
					            </select>
					            <input type="hidden" name="editingPermission" value="${training.editingPermission=='CLIENT' ? 'CLIENT' : 'BOTH' }"/>
		            		</c:when>
		            		<c:otherwise>
		            			<select class="form-control" id="editingPermission" name="editingPermission">
							    	<option value="TRAINER">Only you can manage the training.</option>
					            	<option value="BOTH">You and your client can manage the training.</option>
					            </select>
		            		</c:otherwise>
		            	</c:choose>
		            </div>
	            </div>
            </div>
			<div class="form-group">
	            <div class="col-sm-offset-10 col-sm-10">
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
