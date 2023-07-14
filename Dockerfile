FROM openjdk:17-oracle
COPY target/*.jar assessments.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","assessments.jar"] 

