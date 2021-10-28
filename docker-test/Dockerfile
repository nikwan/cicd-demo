FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN apk update
RUN apk add --no-cache tzdata
RUN cp /usr/share/zoneinfo/Asia/Calcutta /etc/localtime
RUN apk del tzdata
ENTRYPOINT ["java","-jar","/app.jar"]