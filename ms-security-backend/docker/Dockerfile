FROM registry.digitallab.academy:5001/eclipse-temurin:11.0.14_9-jre-alpine
LABEL name="security-service"

COPY ${JAR_FILE} ${APP_DIR}/${APP_NAME}
COPY src/main/resources/rsa-public.pem /security/rsa-public.pem
COPY src/main/resources/rsa-private.pem /security/rsa-private.pem
ENV TZ=America/Lima
EXPOSE 8095

# docker build -t edumar111/security-service:1.0.0 -f ./docker/Dockerfile .
# docker  push edumar111/security-service:1.0.0
