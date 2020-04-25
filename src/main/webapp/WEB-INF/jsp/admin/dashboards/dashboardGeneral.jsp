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

<c:choose>

	<c:when test="${!dataExists}">
                <h2>There are no data</h2>
	</c:when>

	<c:otherwise>
				<div>
					<h3 style="color:green">Clients: </h3> 
					<b>Total number:</b><c:out value="${clients}"></c:out>
					<br><br>

					<h3 style="color:green">Trainers: </h3> 
					<b>Total number:</b><c:out value="${trainers}"></c:out>

					<br><br>			

				</div>
				
				<br><br>
				
				<div>
					<canvas id="canvasClientsPerGuilds"></canvas>
					<%-- <canvas id="canvasPercentageGuildsWhoCompleted"></canvas> --%>
				</div>
	</c:otherwise>
	
</c:choose>


<script>
	$(document).ready(function(){
		var data = {
				labels : [
					<jstl:choose>
					<jstl:when test="${guildNames} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${guildNames}">
							<jstl:out value="\"${item}\"" escapeXml="false"/>,
						</jstl:forEach>						
					</jstl:otherwise>
				</jstl:choose>					
				],
				datasets : [
					{
						label : 'Clients per Guilds',
						backgroundColor : "rgba(22, 38, 212, 0.3)",
						borderColor : "rgba(22, 38, 212, 1)",
						data : [
							
							<jstl:choose>
								<jstl:when test="${clientsPerGuild} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${clientsPerGuild}">
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
		
		canvas = document.getElementById("canvasClientsPerGuilds");
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