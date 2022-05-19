FROM openjdk:17-alpine3.14
WORKDIR /application
COPY jb-hello-world-maven-0.2.1.jar ./
CMD ["java", "-jar", "jb-hello-world-maven-0.2.1.jar"]