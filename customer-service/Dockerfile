FROM openjdk:17.0.2-oracle
COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
ENV CONFIG_SERVICE_URL=""
ENV REGISTRY_SERVICE_URL=""
EXPOSE 8083
ENTRYPOINT ["java","-jar","customer-service.jar"]