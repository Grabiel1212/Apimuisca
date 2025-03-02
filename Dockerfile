# Usar una imagen de OpenJDK 17
FROM openjdk:17

# Crear un directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR de la carpeta target al contenedor
COPY target/*.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
