FROM openjdk:21
WORKDIR investmentapp
COPY target/investmentapp-0.0.1-SNAPSHOT.jar investmentapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "investmentapp.jar"]