<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="tablero">
   
    
    <div class="row">
        <div class="col-md-12">
            <petclinic:board tablero="${tablero}">
            <c:forEach items="${cartasIniciales}" var="carta">
                <c:if test="${carta.posicion!=0 && carta.posicion!=7}">
            	    <petclinic:carta size="100" carta="${carta}"/>
                </c:if>      	
            </c:forEach> 
            </petclinic:board>
        </div>
    </div>

    
</petclinic:layout>
