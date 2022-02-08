# syntax = docker/dockerfile:experimental
FROM maven:3.6.3-jdk-11-slim AS builder

RUN addgroup snapp-pay && adduser --ingroup snapp-pay --disabled-password snaplitto
USER snaplitto

ARG SPRING_PROFILES_ACTIVE
WORKDIR application

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src

#RUN --mount=type=cache,target=/root/.m2 ./mvnw clean install -Pdocker -DskipTests
#RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline

RUN --mount=type=cache,target=/root/.m2 mvn clean package -P ${SPRING_PROFILES_ACTIVE} -DskipTests
#RUN ./mvnw dependency:go-offline -B
#RUN ./mvnw clean package -P ${SPRING_PROFILES_ACTIVE} -DskipTests

RUN cp /application/target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk/openjdk11:alpine-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
