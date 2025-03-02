# Usar una imagen de OpenJDK 17
FROM openjdk:17

# Crear un directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el código fuente al contenedor
COPY . .

# Otorgar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Construir el proyecto con Maven
RUN ./mvnw clean package -DskipTests

# Ejecutar la aplicación
CMD java -jar target/*.jar

