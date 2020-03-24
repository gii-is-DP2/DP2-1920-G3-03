<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<yogogym:layout pageName="guildsCreateOrUpdate">

<h3>Create a new Guild</h3>
	
	<form:form modelAttribute="guild" class="form-horizontal">
	
		<div class="form-group has-feedback">
			<yogogym:inputField label="Creator" name="creator" readonly="true"/>
			<yogogym:inputField label="Logo" name="logo"/>
			<yogogym:inputField label="Name" name="name"/>
			<yogogym:inputField label="Description" name="description"/>
		</div>
	
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" type="submit">Save the Guild</button>
        </div>
       
	</form:form>
	
	
</yogogym:layout>
