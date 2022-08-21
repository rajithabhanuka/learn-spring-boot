FROM openjdk:11
EXPOSE 8080
ADD target/fruit-app.jar fruit-app
ENTRYPOINT ["java","-jar","/fruit-app"]