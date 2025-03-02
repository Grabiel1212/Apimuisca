# Usar una imagen de OpenJDK 17
FROM openjdk:17

# Crear un directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el código fuente al contenedor
COPY . .

# Construir el proyecto con Maven
RUN ./mvnw clean package -DskipTests

# Copiar el archivo JAR generado y ejecutarlo
CMD ["java", "-jar", "$(ls target/*.jar | head -n 1)"]
