FROM openjdk:10.0.1-slim
EXPOSE 8089
VOLUME /tmp
ARG JAR_FILE
RUN mkdir -p target/
COPY ${JAR_FILE} target/
WORKDIR target
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/target/*.jar"]
