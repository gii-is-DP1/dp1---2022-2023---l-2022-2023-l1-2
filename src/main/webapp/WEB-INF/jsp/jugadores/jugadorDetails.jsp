<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="jugadores">

    <h2>Información del Jugador</h2>


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
            <td><b><c:out value="${jugador.usuario.getFechaNacimiento()}"/></b></td>
        </tr>
    </table>
    <c:if test = "${jugadorActual.getNombreUsuario().equals(jugador.usuario.getNombreUsuario())}">
        <div align = "center">
            <a class="btn btn-default" href='<spring:url value="/jugadores/edit/${jugador.id}/" htmlEscape="true"/>'>Editar</a>
        </div>
    </c:if>

    <h2>Estadísticas Globales</h2>
    <table class="table table-striped">
        <tr>
            <th>Partidas Jugadas</th>
            <td><b><c:out value="${jugador.getPartidasJugadas()}"/></b></td>
        </tr>
        <tr>
            <th>Partidas Ganadas</th>
            <td><b><c:out value="${jugador.getPartidasGanadas()}"/></b></td>
        </tr>
        <tr>
            <th>Total Puntos</th>
            <td><b><c:out value="${jugador.getTotalPuntos()}"/></b></td>
        </tr>
        <tr>
            <th>Barcos Usados en Total</th>
            <td><b><c:out value="${numBarcos}"/></b></td>
        </tr>

        <tr>
            <th>Cartas Obtenidas en Total</th>
            <td><b><c:out value="${numCartas}"/></b></td>
        </tr>

        <tr>
            <th>Récord de Puntos</th>
            <td><b><c:out value="${jugador.getRecordPuntos()}"/></b></td>
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
                <th>Duración</th>
                <td><b><c:out value="${estadistica.partida.getDuracion()} minutos"/></b></td>
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
