FROM openjdk:17.0.2-oracle
COPY target/registry-service-0.0.1-SNAPSHOT.jar registry-service.jar
ENV CONFIG_SERVICE_URL=""
EXPOSE 8082
ENTRYPOINT ["java","-jar","registry-service.jar"]