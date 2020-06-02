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
<style>
	.ui-datepicker-calendar
	{
		display:none;
	}
</style>

<script>
	$(function(){		
		$("#monthPicker").datepicker( {
			showButtonPanel: true,
	        dateFormat: 'yy-mm',	        
	        onClose: function(dateText, inst) {
	        	if(!$("#monthPicker").val())
	        	{
                	$(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));	        		
	        	}
            }
		});		
	});
</script>
<form id="form1" onsubmit="/admin/dashboardEquipment/">
		
	<input id="monthPicker" type="text" name="monthAndYear" required="required" pattern="^[0-9]{4}-[0-9]{2}$"/>
	<input type="submit" value="Enviar"/>

</form>
<br>
<h3>Month: <jstl:out value="${month}"/> - <jstl:out value="${year}"/></h3>
	
	<jstl:if test="${hasEquipment}">
		<div>
			<canvas id="canvas"></canvas>
		</div>
	</jstl:if>
	<jstl:if test="${!hasEquipment}">
		<b>Equipment has not been used this month</b><br/>
	</jstl:if>

<script>
<jstl:if test="${hasEquipment}">
$(document).ready(function(){
	var data = {
			labels : [
				<jstl:choose>
					<jstl:when test="${orderName} == null">
						""
					</jstl:when>
					<jstl:otherwise>
						<jstl:forEach var="item" items="${orderName}">
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
							<jstl:when test="${count} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${count}">
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
	
	canvas = document.getElementById("canvas");
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