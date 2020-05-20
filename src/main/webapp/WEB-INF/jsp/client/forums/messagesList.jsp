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
		<button style="display:block; margin:0 auto" onclick="createMessage(this)">Create Message!</button>
	</div>
	
	<c:forEach var="message" items="${messages}">
	<div class="row">
		<div id="${message.id}" class="" style="margin:0 auto; width:70%; padding:20px; background-color:#bdc3c7; padding-top:10px">
			
			<div>
				<div style="float:left; max-width:90%; border-color: #3c6382; border-style: solid; border-width: 0px 0px 0px 5px; padding: 0px 10px;">
					<p><b><c:out value="${message.user.username}"/></b><small style="margin-left:1em; color:rgba(0,0,0,0.6)"><c:out value="${message.createdAt}"/></small></p>
					<c:out value="${message.content}"/>
				</div>
				
				<button style="display:block; float:right;" type="button" style="float:right" onclick="popUpAnswer(this)">Answer</button>
			
			</div>
			
			<br>
			<br>
			
			<ul id="${message.id}a" style="list-style-type:none">
				<c:forEach var="answer" items="${message.answers}">
					<li>
						<br>
						<p><b><c:out value="${answer.user.username}"/></b><small style="margin-left:1em; color:rgba(0,0,0,0.6)"><c:out value="${answer.createdAt}"/></small></p>
						<c:out value="${answer.content}"/>
					</li>
				</c:forEach>
			</ul>
			
		</div>
	</div>
	<br>
	</c:forEach>
	
	<script>
	
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
			
			$(node).load("/client/forums/messagesCreateForm");
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
			node.id = "node"+e.parentNode.id;
			node.style.margin = "10px 0px 0px 0px";
			node.style.backgroundColor = "rgba(0,0,0,0.1)";
			node.style.padding = "10px";
			
			answers.insertBefore(node, answers.childNodes[0]);
			
			$("#node"+e.parentNode.id).load("/client/forums/messagesAnswerForm");
		}
		
		function cancelAnswer(elem) {
			
			elem.parentNode.parentNode.parentNode.parentNode.childNodes[3].disabled = false;
			
			elem.parentNode.parentNode.remove();
		}
		
		function sendAnswer(elem)
		{
			elem.parentNode.action = window.location.href +  "/messages/" + elem.parentNode.parentNode.parentNode.parentNode.id;
			
			elem.parentNode.submit();
		}
		
	</script>
    
</yogogym:layout>
