<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="partidas">
    <h2>¡La partida ha finalizado!</h2>
    <p>
        El ganador es: <c:out value ="${ganador}"/>
    </p>
    <tbody>
        La partida ha durado: <c:out value="${partida.duracion}"/> minutos
    </tbody>
    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Jugador</th>
            <th>Puntuación</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${puntuacion}" var="punt">
            <tr>
                <td>
                    <c:out value="${punt.key}"/>
                </td>
                <td>
                    <c:out value="${punt.value}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>