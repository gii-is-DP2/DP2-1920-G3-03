<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

<yogogym:menuItem active="${name eq 'home'}" url="/" title="home page">
	<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
	<span>Home</span>
</yogogym:menuItem>
				
<yogogym:menuItem active="${name eq 'exercises'}" url="/mainMenu/exercises" title="Excercises">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Exercises</span>
</yogogym:menuItem>
								
<yogogym:menuItem active="${name eq 'machines'}" url="/mainMenu/equipments" title="Equipments">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Equipments</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'quotes'}" url="/mainMenu/quotes" title="Quotes">
	<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
	<span>Quotes</span>
</yogogym:menuItem>

<yogogym:menuItem active="${name eq 'error'}" url="/oups" title="trigger a RuntimeException to see how it is handled">
 	<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
	<span>Error</span>
</yogogym:menuItem>
