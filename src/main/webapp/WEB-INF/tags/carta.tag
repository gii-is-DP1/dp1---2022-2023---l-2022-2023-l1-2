<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Tamaño de la carta a mostrar" %>
 <%@ attribute name="carta" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.carta.Carta"
 description="Carta a ser renderizada" %>
 
 image = document.getElementById('${carta.tipoCarta}');
 ctx.drawImage(image,${carta.getPositionXInPixels(size)},${carta.getPositionYInPixels(size)},${size},${size}+40);
 