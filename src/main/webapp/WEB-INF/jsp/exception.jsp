<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<petclinic:layout pageName="error">

    <spring:url value="/resources/images/homerSimpsonError.png" var="errorImage"/>
    <div style="text-align: center; margin: 20px;">
        <img style="width: 75%;" src="${errorImage}"/>
    </div>
    

    <h2>Ouch!! Algo no ha ido como debería...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
