<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="messages">
    <h2>${guild.name} Forum</h2>

	<c:forEach var="message" items="${messages}">
	<div class="row">
		<div id="${message.id}" class="col-md-6 my-2 p-2" style="background-color:#bdc3c7; padding-top:10px">
			
			<p><b><c:out value="${message.user.username}"/></b><small style="margin-left:1em; color:rgba(0,0,0,0.6)"><c:out value="${message.createdAt}"/></small></p>
			<c:out value="${message.content}"/>
			<button style="float:right" onclick="popUpAnswer(this)">Answer</button>
			
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
		function popUpAnswer(e){
			e.disabled=true;
			
			var answers = document.getElementById(e.parentNode.id+'a');
			var node = document.createElement("DIV");
			var inputContent = document.createElement("INPUT");
			var send = document.createElement("BUTTON");
			send.innerHTML = 'Send';
			var cancel = document.createElement("BUTTON");
			cancel.innerHTML = 'Cancel';
			
			inputContent.setAttribute("type","text");
			node.appendChild(inputContent);
			node.appendChild(send);
			node.appendChild(cancel);
			answers.insertBefore(node, answers.childNodes[0]);
			cancel.onclick = function() {
				cancelAnswer(node,e);
			};
			
		}
		
		function cancelAnswer(node,e){
			e.disabled=false;
			node.remove();
		}
	</script>
    
</yogogym:layout>
