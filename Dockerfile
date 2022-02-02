FROM maven:3.6.0-jdk-11
MAINTAINER EhsanOdyssey <ehsan.odyssey@gmail.com>
COPY . /app

#FROM maven:3.6.0-jdk-11 as maven
#MAINTAINER EhsanOdyssey <ehsan.odyssey@gmail.com>
#COPY ./pom.xml /app/pom.xml
#RUN mvn dependency:go-offline -B
#COPY ./src /app/src
#RUN mvn -f /app/pom.xml clean package -DskipTests
#
#FROM adoptopenjdk/openjdk11:latest
##WORKDIR /my-project
#COPY --from=maven /app/target/snaplitto.jar /app/snaplitto.jar
#ENTRYPOINT ["java", "-jar", "/app/snaplitto.jar"]
