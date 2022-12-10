<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="logros">
    <h2>Partidas</h2>

    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150;">Nombre</th>
            <th style="width: 120px">Tipo</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.getName()}"/>
                </td>
                <td>
                    <a class="btn btn-danger" href='<spring:url value="/logros/delete/${logro.id}" htmlEscape="true"/>'>Eliminar</a>
                </td>
                <td>
                    <a class="btn" href='<spring:url value="/logro/edit/${logro.id}/" htmlEscape="true"/>'>Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <td>
    <a class="btn btn-danger" href='<spring:url value="/logros/create" htmlEscape="true"/>'>Crear logro</a>
    </td>
</petclinic:layout>