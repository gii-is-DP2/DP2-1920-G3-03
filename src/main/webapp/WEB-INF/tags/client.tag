<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Entrenamientos">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Mis Entrenamientos</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Rutinas">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Rutinas</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Dietas">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Dietas</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Clases">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Clases</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'dashboards'}" url="/dashboard" title="Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Dashboard</span>
</yogogym:menuItem>
