FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY ./mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY src src
COPY mvn mvn

RUN chmod a+x ./mvnw && .mvnw package -Dmaven.skip.tests=true

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar target/ssf_15w-0.0.1-SNAPSHOT.jar