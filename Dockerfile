FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.2_13-jre-ubi9-minimal
WORKDIR /usr/local/lib
COPY --from=build /app/target/warehouse-0.0.1-SNAPSHOT.jar ./application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./application.jar"]
