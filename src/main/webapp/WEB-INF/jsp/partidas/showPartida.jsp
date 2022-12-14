<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">

    <c:if test="${message != null}">
        <div class="alert alert-${messageType}">
           <c:out value="${message}"></c:out>
           <a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
        </div>
    </c:if>
    
    <h2>Partida Informacion</h2>


    <table class="table table-striped">
        <tr>
            <th>Codigo</th>
            <td><b><c:out value="${partida.get().codigo}"/></b></td>
        </tr>

        <tr>
            <th>Fecha</th>
            <td><b><c:out value="${partida.get().fecha}"/></b></td>
        </tr>

        <tr>
            <th>Estado</th>
            <td><b><c:out value="${partida.get().estado}"/></b></td>
        </tr>

        <tr>
            <th>Creador</th>
            <td><b><c:out value="${partida.get().creador.getUsuario().getNombreUsuario()}"/></b></td>
        </tr>

        <c:forEach items="${partida.get().jugadores}" var="jugador">
            <tr>
                <th>Jugador: </th>
                <td><b><c:out value="${jugador.getUsuario().getNombreUsuario()}"/></b></td>
            </tr>
        </c:forEach>
        
    </table>
    
    <c:if test = "${partida.get().estado == 'EN_COLA'}">
        <c:if test = "${partida.get().creador.getUsuario().getNombreUsuario().equals(jugador.get().getNombreUsuario())}">
            <div>
                <a class="btn btn-default" href='<spring:url value="/partidas/${partidaId}/inicio" htmlEscape="true"/>'> Empezar Partida</a>
            </div>
        </c:if>
    </c:if>
    
    

</petclinic:layout>