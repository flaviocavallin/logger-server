FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/logfile-server-0.0.1-SNAPSHOT.jar /home/user/logfile-server/logfile-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/home/user/logfile-server/logfile-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
