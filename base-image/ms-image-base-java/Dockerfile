
FROM eclipse-temurin:11.0.14_9-jre-alpine
LABEL maintainer="edumar111@gmail.com"
LABEL type="microservice"

ENV APP_NAME="microservicejava.jar"
ENV BUILD_DIR="build/libs"

ENV APP_DIR="/opt/app"
ENV JAR_FILE="${BUILD_DIR}/*.jar"
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV DEBUG=false
ENV DEBUG_PORT=8000

RUN addgroup java && \
    adduser -D -G java java && \
    mkdir -p ${APP_DIR} && \
    chown java:java ${APP_DIR} 
RUN apk add dos2unix 
WORKDIR ${APP_DIR}
COPY config/entrypoint.sh .
RUN chmod 755 ${APP_DIR}/entrypoint.sh 

USER java

ENTRYPOINT ["sh", "/opt/app/entrypoint.sh"]



# docker build -t edumar111/eclipse-temurin:11.0.14_9-jre-alpine -f Dockerfile .
# docker  push edumar111/eclipse-temurin:11.0.14_9-jre-alpine
