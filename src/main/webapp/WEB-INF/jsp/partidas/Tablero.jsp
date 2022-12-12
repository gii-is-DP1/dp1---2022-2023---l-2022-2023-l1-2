<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="partidas">
   


    
    <div style="background-image: url(/resources/images/Tablero.png);background-size: 600px; height: 710px; width: 600px; background-position: center; " >
        <spring:url value="/resources/images/Tablero.png" htmlEscape="true" var="petsImage"/>
        
        <!--a href='<spring:url value="/session/rolldices" htmlEscape="true"/>'-->
            <div class="container-cube" style="position: relative; left: 500px; top: 150px;">
            
                <div class="cube">
                    <div class="cube-face front">
                        <div class="inside">
                            <span class="dot"></span>
                        </div>
                    </div>
                    <div class="cube-face back">
                        <div class="inside">
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                        </div>
                    </div>
                    <div class="cube-face left">
                        <div class="inside">
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                        </div>
                    </div>
                    <div class="cube-face right">
                        <div class="inside">
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                        </div>
                    </div>
                    <div class="cube-face top">
                        <div class="inside">
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                            <span class="dot"></span>
                        </div>
                    </div>
                    <div class="cube-face bottom">
                        <div class="inside">
                            <span class="dot"></span>
                            <span class="dot"></span>
                        </div>
                    </div>
                </div>
            </div>
        <!--/a-->
        <script src="/resources/js/dado.js"></script>

    </div>

    

   
</petclinic:layout>
