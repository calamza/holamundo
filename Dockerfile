FROM anapsix/alpine-java
COPY *.jar /home/app.jar
WORKDIR /home
CMD ["java","-jar","/home/app.jar"]