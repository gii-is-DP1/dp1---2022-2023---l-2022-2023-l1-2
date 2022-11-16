<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="partidas">
   


    
    <div style="background-image: url(/resources/images/Tablero.png);background-size: 700px; height: 850px; width: 700px; background-position: center; " >
        <spring:url value="/resources/images/Tablero.png" htmlEscape="true" var="petsImage"/>
        
    </div>



   
    
</petclinic:layout>
