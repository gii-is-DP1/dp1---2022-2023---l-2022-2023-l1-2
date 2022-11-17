<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">

    <h2>Jugador Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre de Usuario</th>
            <td><b><c:out value="${jugador.usuario.getNombreUsuario()}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de Nacimiento</th>
            <td><c:out value="${jugador.usuario.getFechaNacimiento()}"/></td>
        </tr>
        <tr>
            <th>Partidas Jugadas</th>
            <td><c:out value="${jugador.getPartidasJugadas()}"/></td>
        </tr>
        <tr>
            <th>Partidas Ganadas</th>
            <td><c:out value="${jugador.getPartidasGanadas()}"/></td>
        </tr>
        <tr>
            <th>Total Puntos</th>
            <td><c:out value="${jugador.getTotalPuntos()}"/></td>
        </tr>
        <tr>
            <th>Récord de Puntos</th>
            <td><c:out value="${jugador.getRecordPuntos()}"/></td>
        </tr>
    </table>

    <spring:url value="{jugadorId}/edit" var="editUrl">
        <spring:param name="jugadorId" value="${jugador.id}"/>
    </spring:url>

    <a href="/" class="btn btn-default">Volver</a>

    <br/>
    <br/>
    <br/>

</petclinic:layout>
