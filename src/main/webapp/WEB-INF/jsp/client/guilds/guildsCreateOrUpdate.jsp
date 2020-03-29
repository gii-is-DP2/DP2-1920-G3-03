<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<yogogym:layout pageName="guildsCreateOrUpdate">

	<c:choose>
		<c:when test="${guild['new']}">
			<h3>Create a new Guild</h3>
		 </c:when>
		 <c:otherwise>
		    <h3>Update a new Guild</h3>
		 </c:otherwise>
	</c:choose>
    
    <form:form modelAttribute="guild" class="form-horizontal">
	
		<div class="form-group has-feedback">
			<yogogym:inputField label="Creator" name="creator" readonly="true"/>
			<yogogym:inputField label="Logo" name="logo"/>
			<yogogym:inputField label="Name" name="name"/>
			<yogogym:inputField label="Description" name="description"/>
		</div>
	
        <div class="col-sm-offset-2 col-sm-10">
            
            <c:choose>
				<c:when test="${guild['new']}">
					<button class="btn btn-default" type="submit">Save the Guild</button>
				 </c:when>
				 <c:otherwise>
				    <button class="btn btn-default" type="submit">Update the Guild</button>
				 </c:otherwise>
			</c:choose>
        </div>
       
	</form:form>
	
	
</yogogym:layout>
