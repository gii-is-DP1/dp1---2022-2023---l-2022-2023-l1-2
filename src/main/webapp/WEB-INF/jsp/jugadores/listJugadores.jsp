<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<petclinic:layout pageName="jugadores">
    <c:if test="${mensaje != null}">
        <div class="alert alert-${messageType}">
            <c:out value="${mensaje}"></c:out>
            <a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
        </div>
    </c:if>
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Nombre de Usuario</th>
            <th>Partidas Jugadas</th>
            <th>Partidas Ganadas</th>
            <th>Puntos Totales</th>
            <th>Récord de Puntos</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores.getContent()}" var="jugador">
            <tr>
                <td>
                    <spring:url value="/jugadores/{jugadorId}" var="jugadorUrl">
                        <spring:param name="jugadorId" value="${jugador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(jugadorUrl)}"><c:out value="${jugador.getUsuario().getNombreUsuario()}"/></a>
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
                    <sec:authorize access="hasAnyAuthority('admin')">
                    <a class="btn btn-danger" href='<spring:url value="/jugadores/delete/${jugador.id}" htmlEscape="true"/>'>Eliminar</a>
                    </sec:authorize>
                </td>
                <td>
                    <sec:authorize access="isAuthenticated()">
                    <a class="btn" href='<spring:url value="/jugadores/edit/${jugador.id}/" htmlEscape="true"/>'>Editar</a>
                    </sec:authorize>
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
        <c:if test = "${autoridad.isPresent()}">
            <td>
                <a class="btn btn-danger" href='<spring:url value="/logros/create" htmlEscape="true"/>'>Crear logro</a>
            </td>
        </c:if>
    </div>
</petclinic:layout>