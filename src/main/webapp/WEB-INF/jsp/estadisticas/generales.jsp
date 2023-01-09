<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="estadisticasGenerales">
    <h2 align = "center">Estadísticas Generales</h2>
    <table id="estadisticasGeneralesTable" class="table table-striped">
        <tr>
            <td>
                <c:out value="Número de partidas juagadas: ${numPartidas}"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:out value="Partida más larga: ${duracionMaxima}"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:out value="Partida más corta: ${duracionMinima}"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:out value="Duración media de las partidas: ${duracionMedia}"/>
            </td>
        </tr>
        <tr>
            <td>
                <c:out value="Número medio de jugadores en las partidas: ${mediaJugadores}"/>
            </td>
        </tr>
    </table>
</petclinic:layout>