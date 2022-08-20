FROM registry.digitallab.academy:5001/eclipse-temurin:11.0.14_9-jre-alpine
LABEL name="product-service"
COPY ${JAR_FILE} ${APP_DIR}/${APP_NAME}
EXPOSE 8091

# docker build -t edumar111/product-service:1.0.0 -f ./docker/Dockerfile .  
# docker  push edumar111/product-service:1.0.0
# docker run -itd --network=onlinestore -p 8091:8091  edumar111/product-service:1.0.0