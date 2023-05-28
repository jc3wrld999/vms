FROM openjdk:8-jdk
RUN rm -rf *.jar
ADD ./build/libs/*.jar app.jar
RUN echo "test..."
EXPOSE 8888
# CMD ["java","-jar","app.jar"]
CMD ["java","-jar","app.jar"]