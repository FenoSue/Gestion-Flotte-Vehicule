FROM openjdk:8

WORKDIR /app

COPY . /app

COPY initial/target/spring-boot-initial-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "spring-boot-initial-0.0.1-SNAPSHOT.jar"]
