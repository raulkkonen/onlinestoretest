FROM redis:6-alpine
COPY redis.conf /usr/local/etc/redis/redis.conf
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf"]
EXPOSE 8087
# docker build -t edumar111/redis-service:1.0.0 -f Dockerfile .
# docker  push edumar111/redis-service:1.0.0

