FROM openjdk:8-jdk-slim as builder

RUN mkdir -p /app/java
WORKDIR /app/java

COPY . .

RUN ./gradlew build

FROM openjdk:8-jre-slim

RUN mkdir -p /app/java
WORKDIR /app/java

COPY --from=builder /app/java/build/unpack/lib BOOT-INF/lib
COPY --from=builder /app/java/build/unpack/app .

CMD ["java", "-server", "-Duser.timezone=Asia/Seoul", "-Dfile.encoding=utf-8", "-Dspring.profiles.active=docker", "org.springframework.boot.loader.JarLauncher"]