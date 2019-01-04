FROM openjdk:10.0.1-slim
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} target/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8089