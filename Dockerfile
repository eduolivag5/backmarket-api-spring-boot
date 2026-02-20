# Fase 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copiamos el pom y el código fuente
COPY pom.xml .
COPY src ./src
# Construimos el jar omitiendo los tests para acelerar el despliegue
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copiamos el jar generado desde la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno para optimizar memoria en Render
# Usamos las que tenías en Railway ajustadas para 512MB
ENV JAVA_TOOL_OPTIONS="-Xms256m -Xmx380m -XX:+UseSerialGC"

EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]