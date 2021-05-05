# Quasar Fire Operation Microservice

Este repositorio contiene el código fuente usado para calcular la posición y contenido del mensaje proveniente de 3 fuentes diferentes.

Para el cálculo de la posición se utilizó el método de trilateración. La trilateración es un método matemático para determinar las posiciones relativas de objetos usando la geometría de triángulos de forma análoga a la triangulación. A diferencia de esta, que usa medidas de ángulo (junto con al menos una distancia conocida para calcular la localización del sujeto), la trilateración usa las localizaciones conocidas de dos o más puntos de referencia, y la distancia medida entre el sujeto y cada punto de referencia. Para determinar de forma única y precisa la localización relativa de un punto en un plano bidimensional usando solo trilateración, se necesitan generalmente al menos 3 puntos de referencia.

Este método nos permite calcular la posición de un punto teniendo como datos iniciales la ubucación de otros 3 puntos y sus respectivas distancias con respecto al punto a calcular. Para esto se utilizó una librería:

https://github.com/lemmingapex/trilateration

```xml
<dependency>
    <groupId>com.lemmingapex.trilateration</groupId>
    <artifactId>trilateration</artifactId>
    <version>1.0.2</version>
</dependency>
```



Para el cálculo del mensaje se desarrolló un algoritmo basado en el cálculo de defasajes proveniente de cada mensaje:


1. La idea del algoritmo es primero identificar cual es el array que contiene la primera palabra, para esto primero calculamos en posición arranca cada array, y de acuerdo a sus tamaños determinamos cual es el que contiene la primer palabra.
2. Teniendo esto podemos calcular cuál es la cantidad total de palabras que tiene el mensaje a reconstruir.
3. Con esta longitud, podemos calcular los desfasajes que tiene cada array de entrada.
4. Una vez calculado los desfasajes, nos queda construir substrings eliminando esos desfasajes de cada array. De esta forma todos los arrays nos quedarán ordenados, independientemente que haya o no un valor en cada posición.
5. Por último lo que nos queda es combnar y reemplazar los substrings antes mencionados.




## Clase MessageCalculator
**getMessage:** Este método se encarga orquestar los llamados a las distintas funciones que nos va a permitir reconstruir el mensaje.

**calculateStartPosition:** Encargado de calcular la posición en la cual cada arreglo tiene el primer valor.

**calculateSubString:** Construye substring en base a longitud y defasaje de cada arreglo.

**replaceArrays:** Suma arrays.

**buildMessage:** Contruye string del mensaje final.



Nota: Alternativamente se desarrollo otra forma de resolverlo teniendo en cuenta anagramas, en este caso es condicion necesario que en los mensajes de entrada al menos se pueda construir un bigrama para reconstruir el mensaje final. El algoritmo se encuentra la clase MessageCalculator2

El proyecto se realizó utilizando estas tecnologías:

* Java 11 con Spring-Boot 2.4.4
* Maven
* Mysql 8 
* JUnit5
* Log4j2


*Para la ejecución local es necesario:*
- Tener Java 11 y Maven 3.6.0.
- Ejecutar mvn spring-boot:run el cuál iniciará el proyecto apuntando a una base de datos en memoria H2.
- Los endpoint tendrán url base /api/v1.


## API Documentation
La  URL base donde está hosteado el servicio es https://quasar-ms.rj.r.appspot.com/

* [Api-docs](https://quasar-ms.rj.r.appspot.com/v2/api-docs)
* [Swagger UI](https://quasar-ms.rj.r.appspot.com/swagger-ui.html#/)
* [Postman collection](https://drive.google.com/file/d/1P_FM_2YQqmUDyu2brJB3x4F0_acGaJiE/view?usp=sharing)
 