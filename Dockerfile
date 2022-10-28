FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar
ENV DATABASE_NAME=fruitshop
ENV PORT=8080
ENV PASSWORD_SECRET=fTWHBbeVMg0Izr59z0UgmuxuWZFVJN
ENV DATABASE_HOST=host.docker.internal
ENV DATABASE_PASSWORD=fl00dw4tch3r
ENV DATABASE_USER=guest01

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]