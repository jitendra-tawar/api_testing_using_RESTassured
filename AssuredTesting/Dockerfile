FROM openjdk:8-jdk-alpine
#WORKDIR /home/jitendra/Desktop/Lise_projects/api_testing_using_RESTassured/AssuredTesting
VOLUME /tmp
#COPY . .
# COPY start.sh .
# ENTRYPOINT ["./start.sh"]
COPY target/*.war app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# Run Application without Docker Container
# ./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar

# Run  Application with Docker Container
    # build image with this command --->>>>>
    # docker build -t rest_assured_testing .

    # run image with this command  --->>>>>
    # docker run rest_assured_testing /bin/sh