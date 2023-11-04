FROM eclipse-temurin:17-jdk-alpine
WORKDIR /
VOLUME /tmp
RUN mkdir -p /logs
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8084
