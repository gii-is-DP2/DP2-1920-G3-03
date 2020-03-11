<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<yogogym:menuItem active="${name eq 'challenges'}" url="/admin/challenges" title="Challenges">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Challenges</span>
</yogogym:menuItem>
					
<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Clases">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Clases</span>
</yogogym:menuItem>
					
<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Dashboard</span>
</yogogym:menuItem>	
					
<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Empleados">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Empleados</span>
</yogogym:menuItem>
