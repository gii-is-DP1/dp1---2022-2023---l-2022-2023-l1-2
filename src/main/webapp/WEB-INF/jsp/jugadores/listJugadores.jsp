<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Owners</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Nombre de Usuario</th>
            <th>Partidas Jugadas</th>
            <th>Partidas Ganadas</th>
            <th>Puntos Totales</th>
            <th>Record de Puntos</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <c:out value="${jugador.getUsuario().getNombreUsuario()}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidasJugadas}"/>
                </td>
                <td>
                    <c:out value="${jugador.partidasGanadas} "/>
                </td>
                <td>
                    <c:out value="${jugador.totalPuntos}"/>
                </td>
                <td>
                    <c:out value="${jugador.recordPuntos}"/>
                </td>
                <td>
                    <a class="btn btn-danger" href='<spring:url value="/jugador/delete/${jugador.id}" htmlEscape="true"/>'>Eliminar</a>
                </td>
                <td>
                    <a class="btn" href='<spring:url value="/jugadores/edit/${jugador.id}/" htmlEscape="true"/>'>Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>