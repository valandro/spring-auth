FROM openjdk:10.0.1-slim

ENV TZ=America/Sao_Paulo

USER root

RUN apt-get install -y bash tzdata

RUN mkdir -p /user/src/app/
COPY dist/java-auth-spring /user/src/app
WORKDIR /user/src/app

RUN export JAVA_OPTS="-server -Xmx512m -Xms512m";

ENTRYPOINT java -jar /user/src/app/*.jar