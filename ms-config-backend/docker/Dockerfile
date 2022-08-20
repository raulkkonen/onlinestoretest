FROM edumar111/eclipse-temurin:11.0.14_9-jre-alpine
LABEL name="config-server"
COPY ${JAR_FILE} ${APP_DIR}/${APP_NAME}
EXPOSE 8081

# docker build -t edumar111/config-service:1.0.0 -f ./docker/Dockerfile .
# docker  push edumar111/config-service:1.0.0
# docker run -itd --name ms-config-backend-svc -p 8081:8081 --network=onlinestore -e GIT_USER=${GIT_USER} -e  GIT_PASSWORD=${GIT_PASSWORD} edumar111/config-service:1.0.0