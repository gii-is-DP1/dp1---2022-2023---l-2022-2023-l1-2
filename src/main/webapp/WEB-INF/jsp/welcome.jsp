<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" style="height: 200px;" src="https://www.fecantri.org/wp-content/uploads/2015/09/Imagen1.png"/>
        </div>
        <div>
            <a class="btn btn-default" href='<spring:url value="/partidas/create" htmlEscape="true"/>'> Crear Partida</a>
        </div>

        <br/>
        <div>
            <a class="btn btn-default" href='<spring:url value="/partidas/join" htmlEscape="true"/>'> Unirse a Partida</a>
        </div>
        <br/>
        <div>
            <a class="btn btn-default" href='<spring:url value="/jugadores/find" htmlEscape="true"/>'> Ver jugadores</a>
        </div>
    </div>
</petclinic:layout>
