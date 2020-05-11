#FROM maven:3.5-jdk-8-alpine

#RUN mkdir -p /src/app
#WORKDIR /src/app
#ADD pom.xml /src/app
#RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
#COPY . /src/app
#RUN ["mvn", "clean", "package"]

#EXPOSE 8080

#CMD ["mvn", "spring-boot:run"]

FROM openjdk:8-jre-alpine
COPY target/future-traffic-2.2.6.RELEASE.jar future-traffic.jar
ENTRYPOINT ["java", "-jar", "future-traffic.jar"]