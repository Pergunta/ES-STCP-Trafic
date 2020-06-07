#FROM maven:3.5-jdk-8-alpine

#RUN mkdir -p /src/app
#WORKDIR /src/app
#ADD pom.xml /src/app
#RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
#COPY . /src/app
#RUN ["mvn", "clean", "package"]

#EXPOSE 8080

#CMD ["mvn", "spring-boot:run"]

#FROM openjdk:8-jre-alpine
#COPY target/future-traffic-2.2.6.RELEASE.jar future-traffic.jar
#ENTRYPOINT ["java", "-jar", "future-traffic.jar"]

#FROM openjdk:8-jre-alpine 
#WORKDIR /usr/local/runme
#RUN apk --no-cache add curl
#RUN curl -X GET http://192.168.160.99:8082/artifactory/libs-release-local/project/future-traffic/2.2.6.RELEASE/future-traffic-2.2.6.RELEASE.jar -O
#ENTRYPOINT ["java","-jar", "future-traffic-2.2.6.RELEASE.jar"]
FROM maven:3.5-jdk-8-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD pom.xml /usr/src/app
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . /usr/src/app
RUN ["mvn","-Dmaven.test.skip", "package" ]

EXPOSE 8888

CMD ["mvn", "spring-boot:run"]