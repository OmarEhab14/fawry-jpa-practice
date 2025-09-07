FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/spring-data-jpa-practice-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
