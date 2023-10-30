FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/mini-bank-accounts-0.0.1-SNAPSHOT.jar mini-bank-accounts-0.0.1-SNAPSHOT.jar
EXPOSE 8001:8001
ENTRYPOINT ["java","-jar","/mini-bank-accounts-0.0.1-SNAPSHOT.jar"]