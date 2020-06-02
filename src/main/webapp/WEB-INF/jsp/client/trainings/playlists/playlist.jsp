<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="tracks">
    <h1><a href ="${playlist.uri}" target="_blank"><c:out value="${playlist.name}"/></a></h1>
    
  <table class="table table-striped">
    <c:forEach var="item" items="${playlist.tracks.items}">
    <tr>
        <th><h2><c:out value="${item.track.name}"/> </h2>  </th>
        <th><c:out value="${item.track.durationMs}"/>s</th>
          <th>  <a href="${item.track.uri}" target="_blank"> reproduce this song on Spotify</a></th>
        <th> <audio controls>
  		<source src="${item.track.previewUrl}" type="audio/mp3">
		</audio></th>
   
        
         </tr>
    </c:forEach>
   
    </table>

</yogogym:layout>