# Spring Application 7 Islas

## ¿Qué es 7 Islas?
7 Islas es una Spring Application hecha para la asignatura de DP1 por Álvaro Vega Rodríguez, Francisco Fernández Mota, Antonio Peláez Moreno, Javier Ulecia García, Gabriel Vidal Tévar y Carlos del Río Pérez. 

### Objetivo
En el juego de mesa 7 Islas tomaremos el papel de los piratas más temibles de este lado del mundo. Nuestro objetivo será conseguir el tesoro más variado y más valioso al finalizar el saqueo de las 7 Islas.

### Cartas
Hay 66 cartas compuestas por:
 - 27 doblones
 - 3 cálices
 - 3 rubíes
 - 3 diamantes
 - 4 collares
 - 4 mapas del tesoro
 - 4 coronas
 - 6 revólveres
 - 6 espadas
 - 6 barriles de ron


### Preparación del juego
1) Se reparten 3 cartas de doblones a los jugadores y se baraja el resto de cartas para formar un mazo.

2) Se coloca una carta del mazo boca arriba en cada una de las islas del 1 al 6 y el mazo restante boca abajo en la Isla 7.

3) Ya estamos listos para jugar.

### Secuencia del juego
7 Islas se juega por turnos y cada turno tiene dos acciones:

#### Acción 1:
1) El jugador lanza el dado.
 - Opcion 1: Llevarse la carta de tesoro de la Isla correspondiente al número obtenido.
 - Opcion 2: Contratar al barco pirata para llevarse un tesoro ubicado en otra isla. Para ello deberá pagar tantas cartas de tesoro como espacios desee moverse sin pasar de la Isla 7 (estas cartas se descartan del juego). Recordar
 que el reverso de las cartas muestra al barco pirata por lo que cualquier carta de tesoro (incluido los doblones) es capaz de contratar al barco para moverse un espacio.

#### Acción 2:
Se repone la isla vacía con una nueva carta de tesoro del mazo colocándola boca arriba.

### Fin del juego
Una vez se acabe el mazo de cartas de tesoro, se completará la ronda actual (para que todos jueguen la misma cantidad de turnos) y terminará el juego.

Para calcular el puntaje final, por cada doblón se obtendrá 1 punto y además se otorgarán puntos por cada set de
tesoros DISTINTOS (no incluye doblones) como tenga el jugador, guiándonos de la tabla mostrada abajo. Puede
tenerse más de un set. Sumamos los puntajes y quien tenga el mayor puntaje, habrá ganado el juego. En caso de empate, quien tenga más doblones será el ganador

<img width="1042" alt="puntaje" src="src\main\resources\static\resources\images\puntaje.png">

## ¿Cómo arrancar la aplicación?
7 Islas es un [Spring Boot](https://spring.io/guides/gs/spring-boot) application construida utilizando [Maven](https://spring.io/guides/gs/maven/). Antes de ejecutar la aplicación debes instalar Maven:

```
mvn install
```

Tendrás acceso a la aplicación en: http://localhost:8080/

<img width="1042" alt="7Islas-screenshot" src="src\main\resources\static\resources\images\7Islas-screenshot.png">


## Configuración de Base de Datos

En su configuración predeterminada, Petclinic utiliza una base de datos en memoria (H2) que se llena al inicio con datos. 

## Trabajar con 7 Islas en tu IDE

### Prerequisitos
Los siguientes ítems deben estas instalados en su sistema:
* Java 8 o superior.
* Herramienta de línea de comandos de git (https://help.github.com/articles/set-up-git)
* Su IDE preferido: 
  * Eclipse con el plugin m2e.
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Pasos:

1) En la línea de comandos
```
git clone https://github.com/gii-is-DP1/spring-petclinic.git
```
2) En Eclipse o STS
```
File -> Import -> Maven -> Existing Maven project
```

Puedes utilizar la línea de comandos `./mvnw generate-resources` o usar el launcher de Eclipse (click derecho en proyecto y `Run As -> Maven install`) para generar el css. Arranca el método principal de la aplicación haciendo click derecho y eligiendo `Run As -> Java Application`.

3) En IntelliJ IDEA

En el menú principal, elige `File -> Open` y selecciona el [pom.xml](pom.xml). Haz click en `Open`.

Los archivos CSS se crean a partir de la construcción de Maven. Puedes hacerlo desde la líea de comandos `./mvnw generate-resources`.

Arranca la aplicación haciendo click derecho en la clase principal `PetClinicApplication` y eligiendo `Run 'PetClinicApplication'`.

4) Navega por 7 Islas.

Visita [http://localhost:8080](http://localhost:8080) en tu navegador.


## ¿Buscas algo en particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|Clase principal | [PetClinicApplication](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java) |
|Archivos de propiedades | [application.properties](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/resources) |
|Caché | [CacheConfiguration](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

# License

La apliación 7 Islas está lanzada bajo la versión 2.0 de la [Licencia de Apache](https://www.apache.org/licenses/LICENSE-2.0).

[spring-petclinic]: https://github.com/spring-projects/spring-petclinic
[spring-framework-petclinic]: https://github.com/spring-petclinic/spring-framework-petclinic
[spring-petclinic-angularjs]: https://github.com/spring-petclinic/spring-petclinic-angularjs 
[javaconfig branch]: https://github.com/spring-petclinic/spring-framework-petclinic/tree/javaconfig
[spring-petclinic-angular]: https://github.com/spring-petclinic/spring-petclinic-angular
[spring-petclinic-microservices]: https://github.com/spring-petclinic/spring-petclinic-microservices
[spring-petclinic-reactjs]: https://github.com/spring-petclinic/spring-petclinic-reactjs
[spring-petclinic-graphql]: https://github.com/spring-petclinic/spring-petclinic-graphql
[spring-petclinic-kotlin]: https://github.com/spring-petclinic/spring-petclinic-kotlin
[spring-petclinic-rest]: https://github.com/spring-petclinic/spring-petclinic-rest
