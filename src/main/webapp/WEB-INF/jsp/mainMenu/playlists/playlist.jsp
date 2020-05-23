<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<yogogym:layout pageName="tracks">
    <h2>Playlist</h2>
    <h3> <c:out value="${playlist.name}"/> </h3>
    <p><c:out value="${playlist.uri}"/></p>
    <%-- <p><c:out value="${playlist.previewUrl}"/></p> --%>
    <c:forEach var="item" items="${playlist.tracks.items}">
        <h2>Track</h2>
        <h3><c:out value="${item.track.name}"/> </h3>
        <p><c:out value="${item.track.uri}"/></p>
        <p><c:out value="${item.track.previewUrl}"/></p>
    </c:forEach>

</yogogym:layout>