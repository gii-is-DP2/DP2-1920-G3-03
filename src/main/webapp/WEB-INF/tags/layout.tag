<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="yogogym" tagdir="/WEB-INF/tags" %>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<yogogym:htmlHeader/>

<body>
<yogogym:bodyHeader menuName="${pageName}"/>

<div class="container-fluid">
    <div class="container xd-container">

        <jsp:doBody/>

        <yogogym:pivotal/>
    </div>
</div>
<yogogym:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
