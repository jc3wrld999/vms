FROM openjdk:8-jdk
ADD ./build/libs/*.jar app.jar
RUN echo "test..."
EXPOSE 8888
CMD ["java","-jar","app.jar"]