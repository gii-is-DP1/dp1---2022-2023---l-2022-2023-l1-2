<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="jugadores">

    <h2>Jugador Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre de Usuario</th>
            <td><b><c:out value="${jugador.usuario.getNombreUsuario()}"/></b></td>
        </tr>
        <tr>
            <th>Nombre </th>
            <td><b><c:out value="${jugador.usuario.getNombre()}"/></b></td>
        </tr>
        <tr>
            <th>Apellidos</th>
            <td><b><c:out value="${jugador.usuario.getApellidos()}"/></b></td>
        </tr>
        <tr>
            <th>Fecha de Nacimiento</th>
            <td><c:out value="${jugador.usuario.getFechaNacimiento()}"/></td>
        </tr>
    </table>

    <h2>Estadísticas Globales</h2>
    <table class="table table-striped">
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
            <th>Barcos Usados en Total</th>
            <td><c:out value="${numBarcos}"/></td>
        </tr>

        <tr>
            <th>Cartas Obtenidas en Total</th>
            <td><c:out value="${numCartas}"/></td>
        </tr>

        <tr>
            <th>Récord de Puntos</th>
            <td><c:out value="${jugador.getRecordPuntos()}"/></td>
        </tr>
    </table>

    <spring:url value="{jugadorId}/edit" var="editUrl">
        <spring:param name="jugadorId" value="${jugador.id}"/>
    </spring:url>

    <h2>Partidas Jugadas</h2>

    <c:forEach items="${estadisticas}" var="estadistica">
        <h3>Partida <c:out value = "${estadisticas.indexOf(estadistica)+1}"/></h3>
        <table class="table table-striped">
            <tr>
                <th>Fecha</th>
                <td><b><c:out value="${estadistica.partida.getFecha()}"/></b></td>
            </tr>
            <tr>
                <th>Ganador</th>
                <td><b><c:out value="${estadistica.partida.getGanador().getUsuario().getNombreUsuario()}"/></b></td>
            </tr>
            <tr>
                <th>Posición</th>
                <td><b><c:out value="${estadistica.posicion}"/></b></td>
            </tr>
            <tr>
                <th>Puntos obtenidos</th>
                <td><b><c:out value="${estadistica.puntosObtenidos}"/></b></td>
            </tr>
            <tr>
                <th>Barcos usados</th>
                <td><b><c:out value="${estadistica.numBarcosUsados}"/></b></td>
            </tr>
            <tr>
                <th>Cartas obtenidas</th>
                <td><b><c:out value="${estadistica.numCartasObtenidas}"/></b></td>
            </tr>
            
        </table>
    </c:forEach>


    <a href="/jugadores/find" class="btn btn-default">Volver</a>

    <br/>
    <br/>
    <br/>

</petclinic:layout>
