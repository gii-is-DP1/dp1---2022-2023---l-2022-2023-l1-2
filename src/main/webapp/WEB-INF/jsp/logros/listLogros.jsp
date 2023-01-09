<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="logros">
    <h2>Tus Logros</h2>
    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 300;">Nombre</th>
            <th style="width: 500;">Descripcion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logrosJugador.getContent()}" var="logroJug">
            <tr>
                <td>
                    <c:out value="${logroJug.getNombreLogro()}"/>
                </td>
                <td>
                    <c:out value="${logroJug.getDescripcion()}"/>
                </td>
                <c:if test = "${autoridad.isPresent()}">
                    <td>
                        <a class="btn btn-danger" href='<spring:url value="/logros/delete/${logroJug.id}" htmlEscape="true"/>'>Eliminar</a>
                    </td>
                    <td>
                        <a class="btn" href='<spring:url value="/logros/edit/${logroJug.id}/" htmlEscape="true"/>'>Editar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center">
        <c:if test="${logrosJugador.number!=0}">
            <a href="?p1=${logrosJugador.number-1}&p2=${logros.number}"><span class="glyphicon glyphicon-arrow-left"></a>
        </c:if>
        <c:if test="${!logrosJugador.last}">
            <a href="?p1=${logrosJugador.number+1}&p2=${logros.number}"><span class="glyphicon glyphicon-arrow-right"></a>
        </c:if>
    </div>
    <h2>Logros</h2>
    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 300;">Nombre</th>
            <th style="width: 500;">Descripcion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros.getContent()}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.getNombreLogro()}"/>
                </td>
                <td>
                    <c:out value="${logro.getDescripcion()}"/>
                </td>
                <c:if test = "${autoridad.isPresent()}">
                    <td>
                        <a class="btn btn-danger" href='<spring:url value="/logros/delete/${logro.id}" htmlEscape="true"/>'>Eliminar</a>
                    </td>
                    <td>
                        <a class="btn" href='<spring:url value="/logros/edit/${logro.id}/" htmlEscape="true"/>'>Editar</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center">
        <c:if test="${logros.number!=0}">
            <a href="?p1=${logrosJugador.number}&p2=${logros.number-1}"><span class="glyphicon glyphicon-arrow-left"></a>
        </c:if>
        <c:if test="${!logros.last}">
            <a href="?p1=${logrosJugador.number}&p2=${logros.number+1}"><span class="glyphicon glyphicon-arrow-right"></span></a>
        </c:if>
        <c:if test = "${autoridad.isPresent()}">
            <td>
                <a class="btn btn-danger" href='<spring:url value="/logros/create" htmlEscape="true"/>'>Crear logro</a>
            </td>
        </c:if>
    </div>
</petclinic:layout>