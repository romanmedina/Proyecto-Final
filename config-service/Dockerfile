FROM openjdk:17.0.2-oracle
COPY ./target/config-service-0.0.1-SNAPSHOT.jar config-service.jar
ENV GIT_USERNAME=romanmedina
ENV GIT_PASSWORD=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
EXPOSE 8081
ENTRYPOINT ["java","-jar","config-service.jar"]