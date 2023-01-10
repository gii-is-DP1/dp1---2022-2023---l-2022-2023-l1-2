<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<petclinic:htmlHeader/>

<body style="background: url(resources/images/19-199730_wiki-pirate-desktop-backgrounds-pic-wpe007053-data-pirate.jpg) no-repeat center center fixed ;background-position: center; background-size: cover;>
<petclinic:bodyHeader menuName="${pageName}"/>

<div class="container-fluid"  >
    <div  >

        <jsp:doBody/>

        <petclinic:pivotal/>
    </div>
</div>
<petclinic:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
