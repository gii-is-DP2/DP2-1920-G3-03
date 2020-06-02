<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="tracks">
    <h1><c:out value="${playlist.name}"/></h1>
    <a href ="${playlist.uri}" target="_blank"> link a la playlist</a>
    
    <c:forEach var="item" items="${playlist.tracks.items}">
        <h2>Track</h2>
        <h3>Name: <c:out value="${item.track.name}"/> </h3>
        <audio controls>
  		<source src="${item.track.previewUrl}" type="audio/mp3">
		</audio>
        <a href="${item.track.uri}" target="_blank"> reproduce this song on the playlist</a>
        <p>Duration: <c:out value="${item.track.durationMs}"/></p>
    </c:forEach>

</yogogym:layout>