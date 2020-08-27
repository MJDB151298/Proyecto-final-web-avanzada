# Archivo que representa la información para la creación de
# una imagen para Docker, indicando todos los parametros necesarios.

# El comando FROM indica la imagen base.
# openjdk:8-jdk-alpine es una imagen de Java 8 muy ligera.
FROM openjdk:11-jdk-slim

# Quien mantiene la versión.
LABEL maintainer="Saul Feliciano <20161534@ce.pucmm.edu.do>"

# Indicando variable de ambiente para pasar el nombre
# de la base de datos.
ENV NOMBRE_APP = 'notificacionesmicroservice'
# forma estandar para cambiar la configuracion
ENV spring.datasource.url='jdbc:mysql://192.168.77.10:3306/notificationsservices'
ENV spring.datasource.username='admin'
ENV spring.datasource.password='admin'

# Añadiendo el punto de montaje en el host
# Por defecto Tomcat crea los archivo temporales en esa ruta,
# lo habilitamos para ver los log, no es necesario para nuestro ejemplo.
VOLUME /tmp

# Puertos que estarán disponibles de nuestra aplicación.
EXPOSE 200-65000

# Copiando el archivo jar generado luego de la ejecución del comando
# gradle task bootjar, se crean el jar y se copia a la imagen.
COPY build/libs/notifications-microservice-0.0.1-SNAPSHOT.jar notifications.jar

#Comando que se ejecuta una vez es iniciada la aplicación.
ENTRYPOINT ["java", "-jar", "notifications.jar"]