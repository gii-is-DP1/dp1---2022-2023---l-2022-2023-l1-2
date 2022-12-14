<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="tablero">
    <c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
		</div>
	</c:if>

    <c:if test="${message2 != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message2}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
		</div>
	</c:if>

    <c:if test="${message3 != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message3}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
		</div>
	</c:if>

    <c:if test="${message4 != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message4}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
		</div>
	</c:if>

    <c:if test="${message5 != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message5}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close"><span aria-hidden="true">&times;</span></a>
		</div>
	</c:if>

   

    <div class="row">
        <div class="column" style="width: 600px;">
            <div class="row">
                <petclinic:board tablero="${tablero}">
                <c:forEach items="${cartasIniciales}" var="carta">
                    <c:if test="${carta.posicion!=0 && carta.posicion!=7}">
                        <petclinic:carta size="100" carta="${carta}"/>
                    </c:if>      	
                </c:forEach> 
                </petclinic:board>
            </div>
        </div>

        
        <div style="position: relative; left: 750px; bottom: 330px; width: 200px; height: 300px;">
            <div class="row">
                
                <div class="container-cube" style=" position:relative ; bottom: 450px; right: 60px;" >
            
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
                <p  > Valor del dado: ${dado} </p>
                <p>Seleccione la Isla a la que quiere viajar</p>
                <form action = "/partidas/${partidaId}/tablero/viajar/">
                    <select name = isla>
                        <c:forEach items="${islas}" var="isla">
                            <option value = ${isla}> ${isla}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Viajar a isla">
                </form>

                <a class="btn btn-default" href='<spring:url value="/partidas/${partidaId}/tablero/cogerCarta" htmlEscape="true"/>'> Quedarse con la carta del dado</a>

                <div>
                    <c:forEach items="${cartasJugador}" var="carJug">
                        <tr>
                            <td>
                                <spring:url value="/resources/images/${carJug.key}.png" var="cartaImage"/>
                                <img src="${cartaImage}" height="100" width="75"/>
                            </td>
                            <td>
                                <c:out value=": ${carJug.value}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </div>
             </div>
            
        </div>

        

        
    
    
    </div>

    <script src="/resources/js/dado.js"></script>



    

    
</petclinic:layout>
   

