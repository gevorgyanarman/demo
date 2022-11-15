from openjdk:17
copy ./build/libs/demo-0.0.1-SNAPSHOT.jar ./app.jar
entrypoint ["java", "-jar", "./app.jar"]
