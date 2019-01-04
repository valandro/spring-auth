FROM openjdk:10.0.1-slim
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} target/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]
EXPOSE 8090
