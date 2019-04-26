#Dockerfile for your app

FROM anapsix/alpine-java:8u144b01_jdk
VOLUME /tmp
ADD target/search-api-0.1.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
