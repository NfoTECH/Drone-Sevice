FROM openjdk:17-alpine
RUN touch /env.txt
RUN printenv > /env.txt
ARG JAR_FILE=/*.jar
COPY target/droneService-0.0.1-SNAPSHOT.jar droneService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","target/droneService-0.0.1-SNAPSHOT.jar"]

# docker buider prune
# docker build -t userName/droneservice:v1 .
# docker login --username=userName
# docker push userName/droneservice:v1

