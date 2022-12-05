<%@ attribute name="tablero" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.tablero.Tablero"
 description="Tablero de juego" %>
<canvas id="canvas" width="${tablero.width}" height="${tablero.height}"></canvas>
<img id="source" src="http://localhost:8080/resources/images/tablero.png" style="display:none">
<img id="BOTELLA" src="http://localhost:8080/resources/images/botella.png" style="display:none">
<img id="BARRIL" src="http://localhost:8080/resources/images/barril.png" style="display:none">
<img id="COLLAR" src="http://localhost:8080/resources/images/collar.png" style="display:none">
<img id="COPA" src="http://localhost:8080/resources/images/copa.png" style="display:none">
<img id="CORONA" src="http://localhost:8080/resources/images/corona.png" style="display:none">
<img id="DIAMANTE" src="http://localhost:8080/resources/images/diamante.png" style="display:none">
<img id="DOBLON" src="http://localhost:8080/resources/images/doblon.png" style="display:none">
<img id="DORSO" src="http://localhost:8080/resources/images/dorso.png" style="display:none">
<img id="ESPADA" src="http://localhost:8080/resources/images/espada.png" style="display:none">
<img id="PISTOLA" src="http://localhost:8080/resources/images/pistola.png" style="display:none">
<img id="RUBI" src="http://localhost:8080/resources/images/rubi.png" style="display:none">

<script>
function drawBoard(){ 
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var image = document.getElementById('source');
    ctx.drawImage(image, 0, 0, ${tablero.width}, ${tablero.height});     
    <jsp:doBody/>
}
window.onload =drawBoard();
</script>