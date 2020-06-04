# FROM maven:3.5-jdk-8-alpine

# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
# ADD pom.xml /usr/src/app
# RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
# COPY . /usr/src/app
# RUN ["mvn", "package","-Dmaven.test.skip=true"]

# EXPOSE 8081

# CMD ["mvn", "spring-boot:run"]

FROM maven:3.5-jdk-8-alpine

# Install git
RUN apt-get update
RUN apt-get install -y git 

# Clone from code repository
RUN mkdir /home/future-traffic
RUN cd /home/future-traffic && git clone https://github.com/Pergunta/ES-STCP-Trafic.git

# Set working directory
WORKDIR /home/future-traffic/ES-STCP-Trafic

# Install requirements and compile code
RUN mvn clean package spring-boot:repackage -DskipTests

EXPOSE 8081

# Run code
ENTRYPOINT ["java","-jar","target/future-traffic-2.2.6.RELEASE.jar"]