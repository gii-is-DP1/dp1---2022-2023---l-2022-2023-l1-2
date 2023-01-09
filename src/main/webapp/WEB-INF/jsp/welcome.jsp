<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home" >
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div align="center">
            <a class="btn btn-default" href='<spring:url value="/jugadores/profile" htmlEscape="true"/>'> Mi Perfil</a>
        </div>
        <br/>
        <div align="center">
            <a class="btn btn-default" href='<spring:url value="/partidas/create" htmlEscape="true"/>'> Crear Partida</a>
        </div>
        <br/>
        <div align="center">
            <a class="btn btn-default" href='<spring:url value="/partidas/join" htmlEscape="true"/>'> Unirse a Partida</a>
        </div>
        <br/>
        <div align="center">
            <a class="btn btn-default" href='<spring:url value="/jugadores/find" htmlEscape="true"/>'> Ver jugadores</a>
        </div>
        <br/>
        <div align="center">
            <a class="btn btn-default" href='<spring:url value="/logros/list?p1=0&p2=0" htmlEscape="true"/>'> Logros</a>
        </div>

        
    </div>
</petclinic:layout>
