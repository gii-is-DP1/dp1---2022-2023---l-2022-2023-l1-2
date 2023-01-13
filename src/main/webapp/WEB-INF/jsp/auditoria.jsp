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

<petclinic:layout pageName="usuarios">

    <h2>Auditoria </h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Nombre de Usuario</th>
            <th>Creador</th>
            <th>Fecha de creación </th>
            <th>Modificador</th>
            <th>Ultima fecha de modificación</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usuarios}" var="usuario">
            <tr>                
                <td>
                    <c:out value="${usuario.nombreUsuario}"/>
                </td>
                <td>
                    <c:out value="${usuario.creator} "/>
                </td>
                <td>
                    <c:out value="${usuario.createdDate}"/>
                </td>
                <td>
                    <c:out value="${usuario.modifier}"/>
                </td>
                
                <td>
                    <c:out value="${usuario.lastModifiedDate}"/>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAnyAuthority('admin')">
        <td>
            <sec:authorize access="isAuthenticated()">
            <a class="btn" href='<spring:url value="/usuario/new" htmlEscape="true"/>'>Crear usuario</a>
            </sec:authorize>
        </td>
    </sec:authorize>
</petclinic:layout>