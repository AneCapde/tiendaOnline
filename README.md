Proyecto Tienda Online
===============================

Configuración
------------- 

**Test Unitarios**

Se puede construir el proyecto y lanzar las pruebas unitarias con el comando

	mvn test -PunitTest

**Base de datos**

Crear una base de datos llamada *jersey* y dar permisos al usuario por defecto

    CREATE DATABASE tiendaonline;
    CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
    GRANT ALL ON tiendaonline.* TO 'spq'@'localhost';

La configuración por defecto para la base de datos y los usuarios puede ser actualizada en el fichero *resources/datanucleus.properties*.

Para compilar el proyecto cada vez que se hace algun cambio

    mvn compile 

**Creacion del proyecto**

Las clases de datos deben ser procesadas antes de generar las tablas con el comando

	mvn datanucleus:enhance
	
**Creación de las tablas**

Para la creación de las tablas se debe ejecutar el comando de maven

    mvn datanucleus:schema-create
    
**Test de Integracion**

Se pueden lanzar las pruebas de Integracion con el comando

	mvn test -PintegrationTest
    
**Test de Rendimiento**

Se pueden lanzar las pruebas de Rendimiento con el comando

    mvn test -PpreformingTest   

**Datos de prueba**

Se pueden introducir datos de prueba en la aplicación utilizando el comando de maven

    mvn exec:java -Pdatos

**Inicio del servidor**

El servidor REST de la aplicación se lanza utilizando el comando

    mvn exec:java

Si el servidor ha sido iniciado correctamente se pueden obtener los datos de prueba accediendo con el navegador a la URL http://localhost:8080/myapp/users.

**Inicio de la aplicación cliente**

La aplicación cliente puede iniciarse usando el comando

    mvn exec:java -Pcliente
    
Documentación
-------------  
   
**Documentación**

Para generar informes de doxygen

	mvn doxygen:report
	
Para copiar el directorio html generado en la carpeta de documentos
	
	mvn validate

Para eliminar los archivos de destino generados, incluidos los documentos de directorio con código html

	mvn clean