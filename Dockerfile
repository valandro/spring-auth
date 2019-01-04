FROM openjdk:10.0.1-slim
ENV TZ=America/Sao_Paulo
USER root
RUN apt-get install -y bash tzdata
RUN mkdir -p /target/
COPY dist/java-auth-spring /target
WORKDIR /target
RUN export JAVA_OPTS="-server -Xmx512m -Xms512m";
ENTRYPOINT java -jar /target/*.jar