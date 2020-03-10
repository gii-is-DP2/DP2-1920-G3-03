<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="challenges">
    
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#start").datepicker({dateFormat: 'yy/mm/dd'});
                $("#end").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    
    <jsp:body>
        <h2>Challenge</h2>

        <form:form modelAttribute="challenge" class="form-horizontal">
        
            <div class="form-group has-feedback">
            	<petclinic:inputField label="Description" name="description"/>
            	<petclinic:inputField label="Start Date" name="start"/>
                <petclinic:inputField label="End Date" name="end"/>
            	<petclinic:inputField label="Reward" name="reward"/>
            	<petclinic:inputField label="Points" name="points"/>
            	<petclinic:inputField label="Repetitions" name="reps"/>
            	<petclinic:inputField label="Weight" name="weight"/>  
            	<h4>Exercise:</h4>
            	<petclinic:inputField label="Exercise name" name="exercise.name"/>
            	<petclinic:inputField label="Description" name="exercise.description"/>
            	<petclinic:inputField label="Kcalories" name="exercise.kcal"/>
            	<petclinic:selectField label="Intensity" name="exercise.intensity" names="${intensities}" size="4"/>
            	<h4>Machine:</h4>
            	<petclinic:inputField label="Machine Name" name="exercise.machine.name"/>
            	<petclinic:inputField label="Location" name="exercise.machine.location"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Save Challenge</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
