#!/bin/sh
echo "DEBUG:$DEBUG"
echo "DEBUG_PORT:$DEBUG_PORT" 
JAVA_OPTS="${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom"
 if [ ! -z "$DEBUG" ] && [ "$DEBUG" == "true" ] && [ ! -z "$DEBUG_PORT" ]; then
    echo "Running the application in debug mode"
    JAVA_OPTS="${JAVA_OPTS} -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:${DEBUG_PORT}"
fi

if [ "$#" -gt 0 ]; then
    echo "App launch parameters '$@'"
    APP_PARAMS=$@
fi

echo "exec java ${JAVA_OPTS} -jar ${APP_DIR}/${APP_NAME} ${APP_PARAMS}"
exec java $JAVA_OPTS -jar $APP_DIR/$APP_NAME $APP_PARAMS
