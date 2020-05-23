<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<yogogym:layout pageName="messages">
   
    <h1 style="text-align: center">${guild.name} Forum</h1>
	
	<div style="margin:0 auto; width:70%; margin-bottom: 15px;">
		<c:if test="${wrongMessage != null}">
			<div class="text-center alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  		<span class="sr-only">Error:</span>
	   			${wrongMessage}
	   		</div>
		</c:if>
		<button style="display:block; margin:0 auto" onclick="createMessage(this)">Create Message!</button>
	</div>
	
	<c:forEach var="message" items="${messages}">
	
	<div>
		<div id="${message.id}" style="margin:0 auto; width:70%; padding:10px; background-color:#bdc3c7;">	
			<div>
				<div style="padding: 10px; background-color:rgba(255,255,255,0.8); float:left; min-width:90%; max-width:90%; border-color: #3c6382; border-style: solid; border-width: 0px 0px 0px 10px; box-shadow: 5px 5px rgba(0,0,0,0.1);">
					<p>
						<b>
							<c:out value="${message.user.username}"/>
						</b>
						
						<small style="float:right; margin-left:1em; color:rgba(0,0,0,0.6)">
							<c:out value="${message.createdAt}"/>
						</small>
					</p>
					
					<br>
					
					<p style="word-break: break-all; word-wrap: wrap;">
						<c:out value="${message.content}"/>
					</p>
				</div>
				
				<button id="answer${message.id}" style="display:block; float:right;" type="button" style="float:right" onclick="popUpAnswer(this)">Answer</button>		
				
				<div style="clear:both"></div>
				
			</div>
			
			<ul id="${message.id}a" style="list-style-type:none;">
				<c:forEach var="answer" items="${message.answers}">
					<li style="background-color:rgba(255,255,255,0.8); margin:10px 0px; padding: 10px;  border-color:#833471; border-style: solid; border-width: 0px 0px 0px 10px;  box-shadow: 5px 5px rgba(0,0,0,0.1); max-width:100%;">
						
						<p>
							<b>
								<c:out value="${answer.user.username}"/>
							</b>
							
							<small style="float:right; margin-left:1em; color:rgba(0,0,0,0.6)">
								<c:out value="${answer.createdAt}"/>
							</small>
							
						</p>
						
						<br>
						<p style="word-break: break-all; word-wrap: wrap;">
							<c:out value="${answer.content}"/>
						</p>
						
					</li>
				</c:forEach>
			</ul>
			
		</div>
	</div>
	<br>
	</c:forEach>
	
	<script>
	
		//Utils
		
		function checkCharacters(textarea,max)
		{	
			var newLines = textarea.value.match(/(\r\n|\n|\r)/g) == null ? 0 : textarea.value.match(/(\r\n|\n|\r)/g).length;
			
			if(textarea.value.length + newLines > max)
				textarea.value = textarea.value.substring(0, max - newLines);	
		}
		
		function prepareState(elem)
		{
			var textarea = $(elem).find("textarea");
			var count = 0;
			
			$(textarea).keyup(function(){
				
				var x = $(textarea).val();
				
		        var newLines = x.match(/(\r\n|\n|\r)/g);
		        var addition = 0;
		        if (newLines != null) {
		            addition = newLines.length;
		        }
				
		        var count = x.length + addition
		        $(elem).find('#charcterWrapper').text(count+"/256");
		        $(elem).find("#charcterWrapper").css('color',"rgb("+count+",0,0)");				
			});
		}
	
		//Create Message
	
		function createMessage(e)
		{
			var br = document.createElement('br');
			
			e.disabled=true;
			var cont = e.parentNode;
			
			var node = document.createElement("DIV");
			node.style.margin = "10px 0px 0px 0px";
			node.style.backgroundColor = "rgba(0,0,0,0.1)";
			node.style.padding = "10px";
			
			cont.appendChild(node);
						
			$(node).load("/client/forums/messagesCreateForm", function(){
				
				var formCont = node.querySelector('#message');
				prepareState(formCont);
				
			});
		}
		
		function cancelMessage(elem) {
			
			elem.parentNode.parentNode.parentNode.childNodes[1].disabled = false;
			elem.parentNode.parentNode.remove();
		}
		
		function sendMessage(elem)
		{
			elem.parentNode.action = window.location.href +  "/messages/create";
			
			elem.parentNode.submit();
		}
		
		
		//Answer
		function popUpAnswer(e){
			
			e.disabled=true;
			var br = document.createElement('br');
			
			var answers = document.getElementById(e.parentNode.parentNode.id+'a');
			
			var node = document.createElement("DIV");
			node.id = "node"+e.parentNode.parentNode.id;
			node.style.margin = "10px 0px 0px 0px";
			node.style.backgroundColor = "rgba(255,255,255,0.8)";
			node.style.borderColor = "#805977"; 
			node.style.borderStyle = "solid"; 
			node.style.borderWidth = "0px 0px 0px 10px";
			node.style.padding = "10px 10px 2px 10px";
			
			answers.insertBefore(node, answers.childNodes[0]);
				
			$(node).load("/client/forums/messagesAnswerForm", function(){
				
				var formCont = node.querySelector('#answer');
				prepareState(formCont);
				
			});
			
		}
		
		function cancelAnswer(elem) {
			
			var but = document.getElementById("answer"+elem.parentNode.parentNode.parentNode.parentNode.id)
			
			but.disabled = false;
			
			elem.parentNode.parentNode.remove();
		}
		
		function sendAnswer(elem)
		{
			elem.parentNode.action = window.location.href +  "/messages/" + elem.parentNode.parentNode.parentNode.parentNode.id;
			
			elem.parentNode.submit();
		}
		
	</script>
    
</yogogym:layout>
