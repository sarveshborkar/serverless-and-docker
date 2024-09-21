FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn test
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app/target/md5-checksum-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080 9010
ENTRYPOINT ["java","-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=9010","-Dcom.sun.management.jmxremote.rmi.port=9010","-Dcom.sun.management.jmxremote.ssl=false","-Dcom.sun.management.jmxremote.authenticate=false","-Djava.rmi.server.hostname=localhost", "-jar", "app.jar"]
