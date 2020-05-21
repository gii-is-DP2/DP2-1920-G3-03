<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<yogogym:layout pageName="home">

        <div class="container">
    	        <div class="row">    	
			<h1 class="text-center"><fmt:message key="welcome"/></h1>
    	        </div> 
				<div class="row">
			<h2 class="text-center"> Kanye West: <c:out value="${quote.quote}"/> </h2>
				</div> 
				<div class = "row">
			<h2 class = "text-center"> API SPOTIFY: <c:out value="${track.name}"/></h2>
				</div>
        </div>
       
        <div class="container">
	    <div class="row">
                    <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
	            <img class="img-responsive" style="display: block;  margin-left: auto;  margin-right: auto;  width: 30%;" src="${petsImage}"/>
	    </div>
        </div>

</yogogym:layout>
