<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="dashboards">
<script src="https://code.jquery.com/jquery-3.4.1.slim.js" integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js" integrity="sha256-R4pqcOYV8lt7snxMQO/HSbVCFRPMdrhAFMH+vr9giYI=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css" integrity="sha256-aa0xaJgmK/X74WM224KMQeNQC2xYKwlAt08oZqjeF0E=" crossorigin="anonymous" />

<form id="form1" onsubmit="/client/${usernameClient}/dashboard">
		
	<input type="month" name="monthAndYear" required />
	<input type="submit" value="Enviar"/>

</form>
<br>
<h3>Month: <jstl:out value="${month}"/> - <jstl:out value="${year}"/></h3>
	<jstl:choose>
		<jstl:when test="${hasRepetitionType or hasKcal or hasBodyParts}">
			<jstl:if test="${hasKcal}">
				<table class="table table-striped">
		        	<tr>
		            	<th>Kcal</th>
		            	<td><b><jstl:out value="${kcal}"/></b></td>
		        	</tr>
				</table>
			</jstl:if>
			<jstl:if test="${!hasKcal}">
				<b>No data for this month</b><br>
			</jstl:if>
			
			<jstl:if test="${hasBodyParts}">
				<div>
					<canvas id="canvasBodyParts"></canvas>
				</div>
			</jstl:if>
			<jstl:if test="${!hasBodyParts}">
				<b>No data for this month</b><br>
			</jstl:if>
			
			<jstl:if test="${hasRepetitionType}">
				<div>
					<canvas id="canvasRepititionType"></canvas>
				</div>
			</jstl:if>
			<jstl:if test="${!hasRepetitionType}">
				<b>No data for this month</b><br>
			</jstl:if>
		</jstl:when>	
		
		<jstl:otherwise>
			<b>No data for this month</b>
		</jstl:otherwise>
	</jstl:choose>
<script>
<jstl:if test="${hasBodyParts}">
$(document).ready(function(){
	var data = {
			labels : [
				<jstl:choose>
					<jstl:when test="${orderBodyParts} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${orderBodyParts}">
							<jstl:out value="\"${item}\"" escapeXml="false"/>,
						</jstl:forEach>						
					</jstl:otherwise>
				</jstl:choose>
			],
			datasets : [
				{
					label : 'Body parts',
					backgroundColor : "rgba(255, 0, 0, 0.3)",
					borderColor : "rgba(255, 0, 0, 1)",
					data : [
						
						<jstl:choose>
							<jstl:when test="${countBodyParts} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${countBodyParts}">
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
	
	canvas = document.getElementById("canvasBodyParts");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "bar",
		data : data,
		options : options
	});
});
</jstl:if>
<jstl:if test="${hasRepetitionType}">
		$(document).ready(function(){
			var data = {
					labels : [
						<jstl:choose>
							<jstl:when test="${orderRepetitionType} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${orderRepetitionType}">
									<jstl:out value="\"${item}\"" escapeXml="false"/>,
								</jstl:forEach>						
							</jstl:otherwise>
						</jstl:choose>
					],
					datasets : [
						{
							label : 'Repetition type',
							backgroundColor : "rgba(0, 255, 0, 0.3)",
							borderColor : "rgba(0, 255, 0, 1)",
							data : [
								
								<jstl:choose>
									<jstl:when test="${countRepetitionType} == null">
										""
									</jstl:when>
									<jstl:otherwise>
										<jstl:forEach var="item" items="${countRepetitionType}">
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
			
			canvas = document.getElementById("canvasRepititionType");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		});
</jstl:if>
</script>

</petclinic:layout>