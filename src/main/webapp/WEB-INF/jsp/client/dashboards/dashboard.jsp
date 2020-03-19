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

	<jstl:if test="${hasExerciseMonth}">
		<table class="table table-striped">
        	<tr>
            	<th>Kcal in the last month</th>
            	<td><b><jstl:out value="${kcalMonth}"/></b></td>
        	</tr>
		</table>
		<div>
			<canvas id="canvasBodyPartsMonth"></canvas>
		</div>
		<div>
			<canvas id="canvasRepititionTypeMonth"></canvas>
		</div>
	</jstl:if>
	<jstl:if test="${!hasExerciseMonth and hasExerciseAll}">
		<b>You haven't exercises in the last month</b>
	</jstl:if>
	<jstl:if test="${hasExerciseAll}">
		<table class="table table-striped">
        	<tr>
            	<th>Kcal Historical</th>
            	<td><b><jstl:out value="${kcalAll}"/></b></td>
        	</tr>
		</table>
		<div>
			<canvas id="canvasBodyPartsAll"></canvas>
		</div>
		<div>
			<canvas id="canvasRepititionTypeAll"></canvas>
		</div>
	</jstl:if>
	<jstl:if test="${!hasExerciseAll}">
		<b>You haven't exercises</b>
	</jstl:if>
<script>
$(document).ready(function(){
	var data = {
			labels : [
				<jstl:choose>
					<jstl:when test="${orderBodyPartsMonth} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${orderBodyPartsMonth}">
							<jstl:out value="\"${item}\"" escapeXml="false"/>,
						</jstl:forEach>						
					</jstl:otherwise>
				</jstl:choose>
			],
			datasets : [
				{
					label : 'Body parts in the last month',
					backgroundColor : "rgba(255, 0, 0, 0.3)",
					borderColor : "rgba(255, 0, 0, 1)",
					data : [
						
						<jstl:choose>
							<jstl:when test="${countBodyPartsMonth} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${countBodyPartsMonth}">
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
	
	canvas = document.getElementById("canvasBodyPartsMonth");
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
						<jstl:when test="${orderBodyPartsAll} == null">
							""
						</jstl:when>
						<jstl:otherwise>
							<jstl:forEach var="item" items="${orderBodyPartsAll}">
								<jstl:out value="\"${item}\"" escapeXml="false"/>,
							</jstl:forEach>						
						</jstl:otherwise>
					</jstl:choose>
				],
				datasets : [
					{
						label : 'Body parts historical',
						backgroundColor : "rgba(255, 0, 0, 0.3)",
						borderColor : "rgba(255, 0, 0, 1)",
						data : [
							
							<jstl:choose>
								<jstl:when test="${countBodyPartsAll} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${countBodyPartsAll}">
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
		
		canvas = document.getElementById("canvasBodyPartsAll");
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
							<jstl:when test="${orderRepetitionTypeMonth} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${orderRepetitionTypeMonth}">
									<jstl:out value="\"${item}\"" escapeXml="false"/>,
								</jstl:forEach>						
							</jstl:otherwise>
						</jstl:choose>
					],
					datasets : [
						{
							label : 'Repetition type in the last month',
							backgroundColor : "rgba(0, 255, 0, 0.3)",
							borderColor : "rgba(0, 255, 0, 1)",
							data : [
								
								<jstl:choose>
									<jstl:when test="${countRepetitionTypeMonth} == null">
										""
									</jstl:when>
									<jstl:otherwise>
										<jstl:forEach var="item" items="${countRepetitionTypeMonth}">
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
			
			canvas = document.getElementById("canvasRepititionTypeMonth");
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
								<jstl:when test="${orderRepetitionTypeAll} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${orderRepetitionTypeAll}">
										<jstl:out value="\"${item}\"" escapeXml="false"/>,
									</jstl:forEach>						
								</jstl:otherwise>
							</jstl:choose>
						],
						datasets : [
							{
								label : 'Repetition type historical',
								backgroundColor : "rgba(0, 255, 0, 0.3)",
								borderColor : "rgba(0, 255, 0, 1)",
								data : [
									
									<jstl:choose>
										<jstl:when test="${countRepetitionTypeAll} == null">
											""
										</jstl:when>
										<jstl:otherwise>
											<jstl:forEach var="item" items="${countRepetitionTypeAll}">
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
				
				canvas = document.getElementById("canvasRepititionTypeAll");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "bar",
					data : data,
					options : options
				});
			});
</script>

</petclinic:layout>