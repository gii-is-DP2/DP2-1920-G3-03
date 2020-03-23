<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<sec:authorize access="isAuthenticated()">
	<c:set var="clientUsername">
		<sec:authentication property="principal.username" />
	</c:set>
</sec:authorize>

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

<yogogym:menuItem active="${name eq 'newChallenges'}" url="/client/${clientUsername}/challenges" title="New Challenges">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>New Challenges</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'myChallenges'}" url="/client/${clientUsername}/challenges/mine" title="My Challenges">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>My Challenges</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'clasifications'}" url="/client/${clientUsername}/clasification" title="Clasification">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Clasification</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'dashboards'}" url="/client/${clientUsername}/dashboard" title="Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Dashboard</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'guilds'}" url="/client/${clientUsername}/guilds" title="Guild">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Guilds</span>
</yogogym:menuItem>
