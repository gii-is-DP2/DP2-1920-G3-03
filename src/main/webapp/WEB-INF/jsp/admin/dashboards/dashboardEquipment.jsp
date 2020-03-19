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
	<jstl:if test="${hasEquipmentWeek}">
		<div>
			<canvas id="canvasWeek"></canvas>
		</div>
	</jstl:if>
	<jstl:if test="${!hasEquipmentWeek and hasEquipmentMonth}">
		<b>Equipment has not been used this week</b>
	</jstl:if>
	<jstl:if test="${hasEquipmentMonth}">
		<div>
			<canvas id="canvasMonth"></canvas>
		</div>
	</jstl:if>
	<jstl:if test="${!hasEquipmentMonth}">
		<b>Equipment has not been used this month</b><br/>
	</jstl:if>
<spring:url value="/admin/equipment/new" var="equipmentUrl"/> 
<a href="${fn:escapeXml(equipmentUrl)}" class="btn btn-default"> New equipment </a>

<script>
$(document).ready(function(){
	var data = {
			labels : [
				<jstl:choose>
					<jstl:when test="${orderNameMonth} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${orderNameMonth}">
							<jstl:out value="\"${item}\"" escapeXml="false"/>,
						</jstl:forEach>						
					</jstl:otherwise>
				</jstl:choose>
			],
			datasets : [
				{
					label : 'Most used equipment in the last month',
					backgroundColor : "rgba(22, 38, 212, 0.3)",
					borderColor : "rgba(22, 38, 212, 1)",
					data : [
						
						<jstl:choose>
							<jstl:when test="${countMonth} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${countMonth}">
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
	
	canvas = document.getElementById("canvasMonth");
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
						<jstl:when test="${orderNameWeek} == null">
							""
						</jstl:when>
						<jstl:otherwise>
							<jstl:forEach var="item" items="${orderNameWeek}">
								<jstl:out value="\"${item}\"" escapeXml="false"/>,
							</jstl:forEach>						
						</jstl:otherwise>
					</jstl:choose>
				],
				datasets : [
					{
						label : 'Most used equipment in the last week',
						backgroundColor : "rgba(22, 38, 212, 0.3)",
						borderColor : "rgba(22, 38, 212, 1)",
						data : [
							
							<jstl:choose>
								<jstl:when test="${countWeek} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${countWeek}">
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
		
		canvas = document.getElementById("canvasWeek");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});

</script>

</petclinic:layout>