FROM registry.digitallab.academy:5001/eclipse-temurin:11.0.14_9-jre-alpine
LABEL name="shopping-service"
COPY ${JAR_FILE} ${APP_DIR}/${APP_NAME}
COPY src/main/resources/rsa-public.pem /security/rsa-public.pem
EXPOSE 8092

# docker build -t edumar111/shopping-service:1.0.0 -f ./docker/Dockerfile .  
# docker  push edumar111/shopping-service:1.0.0
# docker run -itd --network=onlinestore -p 8092:8092  edumar111/shopping-service:1.0.0