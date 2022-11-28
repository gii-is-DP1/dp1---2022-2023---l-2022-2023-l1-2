<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150;">Creador</th>
            <th>Jugadores</th>
            <th style="width: 120px">Fecha</th>
            <th>Hora Inicio</th>
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
                    
                        <spring:url value="/partidas/join/{partidaId}" var="partidaUrl">
                            <spring:param name="partidaId" value="${partida.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(partidaUrl)}">
                            <button style="background: #5E5DF0;border-radius: 999px; box-shadow: #5E5DF0 0 10px 20px -10px; box-sizing: border-box; color: #FFFFFF; cursor: pointer; font-family: Inter,Helvetica,'Apple Color Emoji','Segoe UI Emoji',NotoColorEmoji,'Noto Color Emoji','Segoe UI Symbol','Android Emoji',EmojiSymbols,-apple-system,system-ui,'Segoe UI',Roboto,'Helvetica Neue','Noto Sans',sans-serif; font-size: 14px; font-weight: 700; line-height: 15px; opacity: 1; outline: 0 solid transparent; padding: 6px 18px; user-select: none; -webkit-user-select: none; touch-action: manipulation; width: fit-content;
                            word-break: break-word;
                            border: 0;">Unirse</button>
                        </a>
                    
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>