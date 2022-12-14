<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="logros">
    <h2>
        CREAR LOGRO
    </h2>
    <form:form modelAttribute="logro" class="form-horizontal" id="create-logro-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombreLogro"/>
            <petclinic:inputField label="Descripción" name="descripcion"/>
            <petclinic:inputField label="Objetivo" name="objetivo"/>
            <petclinic:selectField label="Tipo de logro" name="tipoLogro" names="${tipoLogro}" size="5"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Guardar cambios</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
