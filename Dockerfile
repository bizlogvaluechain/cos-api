FROM eclipse-temurin:17-jdk-alpine AS build
FROM maven:3.8.3-openjdk-17 AS maven

WORKDIR /app
#RUN mvn dependency:resolve

COPY . /app
RUN mvn package -DskipTests

# For Java 11,
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=maven /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8084