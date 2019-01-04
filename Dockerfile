FROM openjdk:10.0.1-slim
VOLUME /tmp
ADD /build/libs/springdatajpa-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]