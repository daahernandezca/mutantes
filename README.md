# Mutantes para X-men
Magneto se encuentra en busca de mutantes para su equipo de X-men para lo cual requiere un API donde pueda mandar la secuencia del ADN del individuo a analizar y el API responda si el sujeto puede ingresar a su equipo.

## Indice
1. Tecnologías
2. Setup
   2.1 Instalación
   2.2 Servidor
   4.2 API
4. Testing
5. Algoritmo
6. Arquitectura

## Tecnologías
El API de magneto requirió las siguientes tecnologías:
- Java 11.0.10
- Spring Boot 4
- Junit 5.7.1
- Docker 19.03.13
- Docker-Compose 1.23.2
- AWS SDK 1.11.327
- DynamoDB

## Setup

### Instalación

El API de mutante fue construido bajo tecnología de Java y Spring, pero se dockerizo para la facilidad de implementación de los técnicos X-men.

- Para ejecutar una sola instancia de la API ejecute el siguiente código:

```
  docker pull daahernandezca/mutantes:latest
  docker run -d -p 80:8080 daahernandezca/mutantes 
```
 En este caso el API se expondrá mediante un llamado normal de HTTP, por el puerto por defecto (80).
 
 
 - Para ejecutar tres instancias del API junto con un balanceador de cargas administrado por Caddy2, ejecute archivo el yml de configuración del docker-compose: 
 
 ```
  cd mutantes
  docker-compose pull
  docker-compose up -d && docker-compose scale mutantes=3 
```
Al lado del archivo de yml debe ir también el archivo de configuración del Caddy2 (Caddyfile).

### Servidor

Para la prueba y puesta a punto del API de magneto se escogió los servicios en la nube de AWS así como su base de datos. En AWS se creó un instancia con un sistema operativo CENTOS y se siguió el procedimiento anterior para la instalación con tres servicios y un balanceador de carga.

Las URL expuestas son:
- https://mutantes20ml615438153.online/
- http://mutantes20ml615438153.online/

### API

El API tiene tres llamados que se describen a continuación:

- /:
 ```
GET / ---> This a api for mercado libre Test
 ```
- mutant/:
 ``` 
POST /mutant {
      "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
      }
 ```
 A lo que responde un 200 si el ADN es mutante y 403 si no lo es.
 
 - stats/:
  ``` 
GET /stats ---> {"count_mutant_dna":40, "count_human_dna":100: "ratio":0.4}
 ```
 
 ## Testing
 
 El testing del API de Magneto de ejecuto usando Junit 5 llegando a un 84.3% de cobertura
 
 ![alt text](https://github.com/daahernandezca/mutantes/blob/main/resources/testCoverage.jpg?raw=true)
 
 ## Algoritmo

 El algoritmo que busca los nucleótidos en el ADN se descompone 4 fases como se ve en la figura. 
 
 ![alt text](https://github.com/daahernandezca/mutantes/blob/main/resources/algoritmo.jpg?raw=true)
 
 Donde el algoritmo empieza a ver las repeticiones horizontales y luego la verticales. Luego un algoritmo que saca las diagonales hacia arriba se corre y se evalúa las repeticiones. Para las otras diagonales se rota la matriz se utiliza el mismo método que la diagonal anterior y se evalúan repeticiones.
 
 La matriz que saca la diagonal entrega la matriz en la siguiente forma: (donde se puede verificar fácilmente las repeticiones)
 
 ```
 T
 C,T
 T,A,G
 A,T,G,C
 C,G,A,T,A
 T,T,A,T,G,A
 C,C,A,G,C
 A,C,G,T
 C,T,G
 T,A
 G
  ```
  
 ## Arquitectura
 
  ![alt text](https://github.com/daahernandezca/mutantes/blob/main/resources/aquitectura.jpg?raw=true)
  
  Como se ve en la figura se desarrolló en Eclipse IDE con el framework Spring Boot programando el API con conexión a DynamoDB. Lo que se dockerizo y se armó una red con docker-compose de un balanceador de carga con caddy y 3 instancias del docker de la API para mejorar el rendimiento en las pruebas de carga que se realizaron con JMeter. 
 
  
