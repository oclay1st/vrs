FROM eclipse-temurin:21-jre-alpine

COPY ./application /app

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=60",  "-jar", "vrs-1.0.0.jar" ]
