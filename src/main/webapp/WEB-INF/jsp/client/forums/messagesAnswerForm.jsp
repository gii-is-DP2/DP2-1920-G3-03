<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form modelAttribute="message" class="form-horizontal">
	
	<p>Answer:</p>
	
	<textarea maxlength="256" name="content" style="padding:15px; margin: 10px 0px; max-width: 100%; min-width: 100%; min-height: 90px; max-height: 180px; border: none; background-color: rgba(255,255,255,0.3)"></textarea>
	
	<br>
	
		<button type="button" onclick="sendAnswer(this)">Send</button>
	<button type="button" onclick="cancelAnswer(this)">Cancel</button>
		       
</form:form>