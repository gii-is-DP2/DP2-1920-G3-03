<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="redirect">
<form action = "https://accounts.spotify.com/api/token" method = "POST">
	<input type="hidden" name="code" value= "${code}"/>
	<input type="hidden" name="grant_type" value = "authorization_code"/>
	<input type="hidden" name="Authorization" value="OTU2YjhhZTNlNGIyNDZiNmE4MmM0YTJjNWNlNmU0YWM6ZDkyMGEyOGFjOTU4NDU5ZWIzNTRmODIyMjZkYmFmMTY="/>
	<input type="hidden" name ="redirect_uri" value = "http://localhost:8080/callback/"/>
	<button>amigodale</button>
</form>
</yogogym:layout>