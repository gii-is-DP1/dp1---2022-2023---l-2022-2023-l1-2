<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Owners</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 100px;">ID</th>
            <th style="width: 150;">Creador</th>
            <th>Jugadores</th>
            <th style="width: 120px">Fecha</th>
            <th>Hora Inicio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas}" var="partida">
            <tr>
                <td>
                    <spring:url value="/partidas/join/{partidaId}" var="partidaUrl">
                        <spring:param name="partidaId" value="${partida.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(partidaUrl)}"><c:out value="${partida.id}"/></a>
                </td>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>