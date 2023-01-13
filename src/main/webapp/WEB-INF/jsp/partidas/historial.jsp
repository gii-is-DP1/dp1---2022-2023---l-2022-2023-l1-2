<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="partidas">
    <h2>Historial de partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Creador</th>
            <th>Jugadores</th>
            <th>Fecha</th>
            <th>Hora Inicio</th>
            <th>Hora Final</th>
            <th>Código</th>
            <th>Estado</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
            
                <td>
                    <c:out value="${partida.creador.getUsuario().getNombreUsuario()}"/>
                </td>
                <td>
                    <c:forEach var="jugador" items="${partida.jugadores}">
                        <c:out value="${jugador.getUsuario().getNombreUsuario()} "/>
                    </c:forEach>
                </td>
                <td>
                    <c:out value="${partida.fecha}"/>
                </td>
                <td>
                    <c:out value="${partida.horaInicio}"/>
                </td>
                <td>
                    <c:if test="${partida.estado != 'FINALIZADA'}">- </c:if><c:out value="${partida.horaFin}"/>
                </td>
                <td>
                    <c:out value="${partida.codigo}"/>
                </td>
                <td>
                    <c:out value="${partida.estado}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>