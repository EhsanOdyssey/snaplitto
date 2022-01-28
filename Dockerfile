FROM adoptopenjdk/openjdk11:latest
MAINTAINER EhsanOdyssey <ehsan.odyssey@gmail.com>
ARG JAR_FILE
COPY ${JAR_FILE} snaplitto.jar
EXPOSE 8020
ENTRYPOINT ["java","-jar","/snaplitto.jar"]
