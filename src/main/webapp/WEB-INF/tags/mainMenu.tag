<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<yogogym:menuItem active="${name eq 'home'}" url="/" title="home page">
	<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
	<span>Home</span>
</yogogym:menuItem>
				
<yogogym:menuItem active="${name eq 'owners'}" url="/ejercicios/find" title="Ejercicios">
	<span class="glyphicon glyphicon-" aria-hidden="true"></span>
	<span>Ejercicios</span>
</yogogym:menuItem>
								
<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Maquinas">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Maquinas</span>
</yogogym:menuItem>
				
<yogogym:menuItem active="${name eq 'vets'}" url="/vets" title="Retos">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Retos</span>
</yogogym:menuItem>
	
<yogogym:menuItem active="${name eq 'error'}" url="/oups" title="trigger a RuntimeException to see how it is handled">
 	<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
	<span>Error</span>
</yogogym:menuItem>
