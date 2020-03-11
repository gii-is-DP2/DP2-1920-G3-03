<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<sec:authorize access="isAuthenticated()">
	<c:set var="trainerUsername">
		<sec:authentication property="principal.username" />
	</c:set>
</sec:authorize>

<yogogym:menuItem active="${name eq 'clients'}" url="/trainer/${trainerUsername}/clients" title="Clients">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>My Clients</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'trainings'}" url="/trainer/${trainerUsername}/trainings" title="Training">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Training Management</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'routines'}" url="/trainer/${trainerUsername}/routines" title="Routines">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Routine Management</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Dietas">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Gestion Dietas</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'vets'}" url="/vets"
	title="Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Dashboard</span>
</yogogym:menuItem>
