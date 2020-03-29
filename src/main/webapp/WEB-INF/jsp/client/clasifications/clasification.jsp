<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script src="https://code.jquery.com/jquery-3.4.1.slim.js" integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" integrity="sha256-aa0xaJgmK/X74WM224KMQeNQC2xYKwlAt08oZqjeF0E=" crossorigin="anonymous" />

<petclinic:layout pageName="challenges">
    <h2>Challenges completed</h2>

	<c:if test="${hasChallenge}">
    	<table id="challengesTable" class="table table-striped">
        	<thead>
        	<tr>
        		<th>Name</th>
        		<th>Description</th>
            	<th>Points</th>
        	</tr>
        	</thead>
        	<tbody>
        	<c:forEach items="${challenges}" var="challenge">
            	<tr>
                	<td>
                    	<c:out value="${challenge.name}"/>
                	</td>
                	<td>
                    	<c:out value="${challenge.description}"/>
                	</td>
                	<td>
                    	<c:out value="${challenge.points}"/>
                	</td>
            	</tr>
        	</c:forEach>
        		<tr>
        			<td>
        			</td>
        			<td>
        			</td>
        			<td>
        				Total points: <c:out value="${totalPoint}"/>
        			</td>
        		</tr>
        		<tr>
        			<td>
        			</td>
        			<td>
        			</td>
        			<td>
        				Historical position: <c:out value="${positionAll}"/>
        			</td>
        		</tr>
        		<c:if test="${hasPositionWeek}">
        			<tr>
        				<td>
        				</td>
        				<td>
        				</td>
        				<td>
        					Week position: <c:out value="${positionWeek}"/>
        				</td>
        			</tr>
        		</c:if>
        	</tbody>
    	</table>
	</c:if>
	<c:if test="${!hasChallenge && hasChallengeClasificationAll}">
		You haven't completed challenges
	</c:if>
	<h2>Week Clasification</h2>
	<c:if test="${hasChallengeClasificationWeek}">
		<div>
			<canvas id="canvasClasificationWeek"></canvas>
		</div>
	</c:if>
    <c:if test="${!hasChallengeClasificationWeek && hasChallengeClasificationAll}">
		No one has completed challenges this week
	</c:if>
	<h2>Historical Clasification</h2>
	<c:if test="${hasChallengeClasificationAll}">
		<div>
			<canvas id="canvasClasificationAll"></canvas>
		</div>
	</c:if>
    <c:if test="${!hasChallengeClasification}">
		No one has completed challenges
	</c:if>
</petclinic:layout>
<script>

<c:if test="${hasChallengeClasificationWeek}">
$(document).ready(function(){
	var data = {
			labels : [
				<c:choose>
					<c:when test="${orderUserWeek} == null">
						""
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${orderUserWeek}">
							<c:out value="\"${item}\"" escapeXml="false"/>,
						</c:forEach>						
					</c:otherwise>
				</c:choose>
			],
			datasets : [
				{
					label : 'Week clasification',
					backgroundColor : "rgba(255, 0, 0, 0.3)",
					borderColor : "rgba(255, 0, 0, 1)",
					data : [
						
						<c:choose>
							<c:when test="${orderPointWeek} == null">
								""
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${orderPointWeek}">
									<c:out value="\"${item}\"" escapeXml="false"/>,
								</c:forEach>				
							</c:otherwise>
						</c:choose>							
						
					]
				}
			]
	};
	var options = {
		scales : {
			xAxes : [
				{
					ticks : {
						min : 0,
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
	
	canvas = document.getElementById("canvasClasificationWeek");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "horizontalBar",
		data : data,
		options : options
	});
});
</c:if>
<c:if test="${hasChallengeClasificationAll}">
$(document).ready(function(){
	var data = {
			labels : [
				<c:choose>
					<c:when test="${orderUserAll} == null">
						""
					</c:when>
					<c:otherwise>
						<c:forEach var="item" items="${orderUserAll}">
							<c:out value="\"${item}\"" escapeXml="false"/>,
						</c:forEach>						
					</c:otherwise>
				</c:choose>
			],
			datasets : [
				{
					label : 'Clasification historical',
					backgroundColor : "rgba(0, 255, 0, 0.3)",
					borderColor : "rgba(0, 255, 0, 1)",
					data : [
						
						<c:choose>
							<c:when test="${orderPointAll} == null">
								""
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${orderPointAll}">
									<c:out value="\"${item}\"" escapeXml="false"/>,
								</c:forEach>				
							</c:otherwise>
						</c:choose>							
						
					]
				}
			]
	};
	var options = {
		scales : {
			xAxes : [
				{
					ticks : {
						min : 0,
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
	
	canvas = document.getElementById("canvasClasificationAll");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "horizontalBar",
		data : data,
		options : options
	});
});
</c:if>
</script>