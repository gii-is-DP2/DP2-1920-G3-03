<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<yogogym:menuItem active="${name eq 'challenges'}" url="/admin/challenges" title="Challenges">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Challenges</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'submittedInscriptions'}" url="/admin/inscriptions/submitted" title="Submitted Inscriptions">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Submitted Inscriptions</span>
</yogogym:menuItem>
					
<yogogym:menuItem active="${name eq 'equipmentDashboard'}" url="/admin/dashboardEquipment" title="Equipment Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Equipment Dashboard</span>
</yogogym:menuItem>	

<yogogym:menuItem active="${name eq 'challengesDashboard'}" url="/admin/dashboardChallenges" title="Challenges Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Challenges Dashboard</span>
</yogogym:menuItem>	

<yogogym:menuItem active="${name eq 'generalDashboard'}" url="/admin/dashboardGeneral" title="General Dashboard">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>General Dashboard</span>
</yogogym:menuItem>	
<%--
<yogogym:menuItem active="${name eq 'vets'}" url="/" title="Empleados">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Trainers</span>
</yogogym:menuItem>
 --%>
