<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="challenges">
    
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#initialDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    
    <jsp:body>
        <h2>Challenge</h2>

        <form:form modelAttribute="challenge" class="form-horizontal">
        
            <div class="form-group has-feedback">
            	<petclinic:inputField label="Name" name="name"/>
            	<petclinic:inputField label="Description" name="description"/>
            	<petclinic:inputField label="Initial Date" name="initialDate"/>
                <petclinic:inputField label="End Date" name="endDate"/>
            	<petclinic:inputField label="Reward" name="reward"/>
            	<petclinic:inputField label="Points" name="points"/>
            	<petclinic:inputField label="Repetitions" name="reps"/>
            	<petclinic:inputField label="Weight" name="weight"/>  
            </div>
            
            <h3>Exercise</h3>
            
            <select id="exerciseId" name="exerciseId" required="required">
				<c:forEach var="exercise" items="${exercises}">
					<option value="${exercise.id}">${exercise.name}</option>
				</c:forEach>
			</select>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Save Challenge</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
