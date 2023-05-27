FROM openjdk:8-jdk
ADD ./build/libs/*.jar app.jar
EXPOSE 8888
CMD ["java","-jar","app.jar"]