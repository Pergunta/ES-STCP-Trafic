
FROM openjdk:8-jre-slim-buster AS fe
WORKDIR /usr/local/runme
COPY target/futureTraffic-0.0.1.1.jar futureTraffic.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","futureTraffic.jar"]
