<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="ranking">
    <h2>Ranking por puntos totales</h2>
    <div style="text-align: right">
        <a href="/jugadores/rankingPartidasGanadas" class="btn btn-default">Por Partidas Ganadas</a>
    </div>
    <table id="rankingTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 200px;">Posición</th>
                <th>Nombre de Usuario</th>
                <th>Puntos Totales</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${jugadores.getContent()}" var="jugador">
                <tr>
                    <td>
                        <c:out value="${jugadores.number*5+jugadores.getContent().indexOf(jugador)+1}"/>
                    </td>
                    <td>
                        <spring:url value="/jugadores/{jugadorId}" var="jugadorUrl">
                            <spring:param name="jugadorId" value="${jugador.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(jugadorUrl)}"><c:out value="${jugador.getUsuario().getNombreUsuario()}"/></a>
                    </td>
                    
                    <td>
                        <c:out value="${jugador.totalPuntos}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div align="center">
        <c:if test="${jugadores.number!=0}">
            <a href="?page=${jugadores.number-1}"><span class="glyphicon glyphicon-arrow-left"></a>
        </c:if>
        <c:if test="${!jugadores.last}">
            <a href="?page=${jugadores.number+1}"><span class="glyphicon glyphicon-arrow-right"></span></a>
        </c:if>
    </div>
</petclinic:layout>