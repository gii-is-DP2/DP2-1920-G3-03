<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>


<yogogym:layout pageName="challenges">
    
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
        <div class="form-group">
            <div class="form-group has-feedback">
            	<input type="hidden" name="id" id="id" class="form-control" value="${challenge.id}"/>
            	<yogogym:inputField label="Name" name="name"/>
            	<yogogym:inputField label="Description" name="description"/>
            	<yogogym:inputField label="Initial Date" name="initialDate"/>
                <yogogym:inputField label="End Date" name="endDate"/>
            	<yogogym:inputField label="Reward" name="reward"/>
            	<yogogym:inputField label="Points" name="points"/>
            	<yogogym:inputField label="Repetitions" name="reps"/>
            	<yogogym:inputField label="Weight" name="weight"/>  
		       <yogogym:selectField label="Exercise" name="exercise.id" size="" map="${exercises}"/>
            </div>
         
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button class="btn btn-default" type="submit">Save Challenge</button>
                </div>
            </div>
        </div>
        </div>    
        </form:form>

    </jsp:body>

</yogogym:layout>
