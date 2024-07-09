FROM maven:3.9.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/game-0.0.1.jar .
EXPOSE 8080

ENV MASTER_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tmp
ENV MASTER_DATASOURCE_USERNAME=postgres
ENV MASTER_DATASOURCE_PASSWORD=root

ENV READ_REPLICA_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tmp
ENV READ_REPLICA_DATASOURCE_USERNAME=postgres
ENV READ_REPLICA_DATASOURCE_PASSWORD=rootENV 

EXPOSE 8080

CMD ["java", "-jar", "game-0.0.1.jar"]
