<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<petclinic:layout pageName="dashboards">
<script src="https://code.jquery.com/jquery-3.4.1.slim.js" integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" integrity="sha256-aa0xaJgmK/X74WM224KMQeNQC2xYKwlAt08oZqjeF0E=" crossorigin="anonymous" />


<form id="form1" onsubmit="/admin/dashboardChallenges/">
		
	<input type="month" name="monthAndYear"/>
	<input type="submit" value="Enviar"/>

</form>
<br>
<h3>Month: <c:out value="${month}"/> - <c:out value="${year}"/></h3>

<c:choose>

	<c:when test="${!ChallengesExists}">
                <h2>There are no challenges ending this month. Create one!</h2>
                <a class="btn btn-default" href='<spring:url value="/admin/challenges/new" htmlEscape="true"/>'>Create Challenge</a>
	</c:when>


	<c:otherwise>
		<c:choose>
			<c:when test="${NoCompletedChallenges}">
				<h2>No challenge is completed</h2>
			</c:when>
			
			<c:otherwise>
				<div>
					<h3 style="color:green">Client with more points this month: </h3> 
					<h5><b><c:out value="${client}"/></b>  (<c:out value="${email}"/>)</h5>
					<b>Points:</b><c:out value="${cPoints}"></c:out>
					
					<br><br>
								
					<h3 style="color:green">Guild with more points this month: </h3> 
					<h5><b><c:out value="${guild}"></c:out></b></h5>
					<b>Points:</b><c:out value="${gPoints}"></c:out>
				</div>
				
				<br><br>
				
				<div>
					<canvas id="canvasPercentageClientsWhoCompleted"></canvas>
					<canvas id="canvasPercentageGuildsWhoCompleted"></canvas>
				</div>
			</c:otherwise>
		</c:choose>       
	</c:otherwise>
	
</c:choose>


<script>
	$(document).ready(function(){
		var data = {
				labels : [
					<jstl:choose>
					<jstl:when test="${challengesNames} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${challengesNames}">
							<jstl:out value="\"${item}\"" escapeXml="false"/>,
						</jstl:forEach>						
					</jstl:otherwise>
				</jstl:choose>					
				],
				datasets : [
					{
						label : 'Percentage of Clients who completed each Challenge',
						backgroundColor : "rgba(22, 38, 212, 0.3)",
						borderColor : "rgba(22, 38, 212, 1)",
						data : [
							
							<jstl:choose>
								<jstl:when test="${percentageClients} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${percentageClients}">
										<jstl:out value="\"${item}\"" escapeXml="false"/>,
									</jstl:forEach>				
								</jstl:otherwise>
							</jstl:choose>							
							
						]
					}
				]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							min : 0,
							stepSize : 1,
							autoSkip : true
						}
					}
				]
			},
			legend : {
				display : true
			}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvasPercentageClientsWhoCompleted");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
		$(document).ready(function(){
    		var data = {
    				labels : [
    					<jstl:choose>
    					<jstl:when test="${challengesNames} == null">
    						""
    					</jstl:when>
    					<jstl:otherwise>
    						<jstl:forEach var="item" items="${challengesNames}">
    							<jstl:out value="\"${item}\"" escapeXml="false"/>,
    						</jstl:forEach>						
    					</jstl:otherwise>
    				</jstl:choose>					
    				],
    				datasets : [
    					{
    						label : 'Percentage of Guilds who completed each Challenge',
    						backgroundColor : "rgba(100, 38, 212, 0.3)",
    						borderColor : "rgba(22, 38, 212, 1)",
    						data : [
    							
    							<jstl:choose>
    								<jstl:when test="${percentageGuilds} == null">
    									""
    								</jstl:when>
    								<jstl:otherwise>
    									<jstl:forEach var="item" items="${percentageGuilds}">
    										<jstl:out value="\"${item}\"" escapeXml="false"/>,
    									</jstl:forEach>				
    								</jstl:otherwise>
    							</jstl:choose>							
    							
    						]
    					}
    				]
    		};
    		var options = {
    			scales : {
    				yAxes : [
    					{
    						ticks : {
    							min : 0,
    							stepSize : 1,
    							autoSkip : true
    						}
    					}
    				]
    			},
    			legend : {
    				display : true
    			}
    		};
    		
    		var canvas, context;
    		
    		canvas = document.getElementById("canvasPercentageGuildsWhoCompleted");
    		context = canvas.getContext("2d");
    		new Chart(context, {
    			type : "bar",
    			data : data,
    			options : options
    		});
    	});
</script>


</petclinic:layout>