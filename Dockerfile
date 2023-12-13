#FROM eclipse-temurin:17-jdk-alpine AS build
#FROM maven:3.8.3-openjdk-17 AS maven
#
#WORKDIR /app
##RUN mvn dependency:resolve
#
#COPY . /app
#RUN mvn package -DskipTests
#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR /
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
#EXPOSE 8084
# Build stage
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app
COPY . /app
RUN mvn package -DskipTests

# Second stage
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /
VOLUME /tmp

# Copy the JAR from the build stage to the current stage
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8084
