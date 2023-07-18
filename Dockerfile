FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} giphy.jar
ENTRYPOINT ["java","-jar","/giphy.jar"]