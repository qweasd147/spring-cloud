FROM openjdk:8-jdk-slim

RUN mkdir -p /app/java
WORKDIR /app/java

COPY . .

RUN ./gradlew build

CMD ["java", "-jar", "-server", "-Duser.timezone=Asia/Seoul", "-Dfile.encoding=utf-8", "build/libs/config-server-1.0.0-SNAPSHOT.jar"]