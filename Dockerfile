FROM eclipse-temurin:17-jdk-focal
LABEL authors="SAOM"

EXPOSE 8080
COPY target/ws-demo-management-0.0.1-SNAPSHOT.jar ws-demo-management-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/ws-demo-management-0.0.1-SNAPSHOT.jar"]