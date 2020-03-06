<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true" %>

	<li class="dropdown">
	    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Menu<span class="caret"></span></a>
	    
	    <ul class="dropdown-menu">
	        <yogogym:mainMenu name="${menuName}"/>
		</ul>
	</li>
	   
	   
<sec:authorize access="hasAuthority('admin')"> 
	 <li class="dropdown">
	 	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin<span class="caret"></span></a>
	    <ul class="dropdown-menu">
	        <yogogym:admin name="${menuName}"/>
		</ul>
	 </li>
</sec:authorize>

<sec:authorize access="hasAuthority('trainer')">
	 <li class="dropdown">
	    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Trainer<span class="caret"></span></a>
	 
	    <ul class="dropdown-menu">
	        <yogogym:trainer name="${menuName}"/>
		</ul>
	</li>
</sec:authorize>

<sec:authorize access="hasAuthority('user')">
	<li class="dropdown">
	    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Client<span class="caret"></span></a>
		
	    <ul class="dropdown-menu">
	        <yogogym:client name="${menuName}"/>
		</ul>
	</li>
</sec:authorize>
