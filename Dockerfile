#FROM eclipse-temurin:17-jdk-alpine
#WORKDIR /
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
#EXPOSE 8091

#### Stage 1: Build the application
#FROM openjdk:8-jdk-alpine as build
FROM eclipse-temurin:17-jdk-alpine

# Set the current working directory inside the image
WORKDIR /app
#WORKDIR /

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline.
# This is a separate step so the dependencies will be cached unless
# the pom.xml file has changed.
#RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw clean package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

RUN ls -lrt
COPY target/*.jar app.jar

#### Stage 2: A minimal docker image with command to run the app
#FROM openjdk:8-jre-alpine
FROM eclipse-temurin:17-jre-alpine

#ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#RUN ls -lrt
#COPY target/*.jar app.jar

#ENTRYPOINT ["java","-cp","app:app/lib/*","com.bizlog.rms.COSApiApplication"]
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8081
