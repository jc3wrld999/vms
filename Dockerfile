FROM openjdk:8-jdk
ADD ./build/libs/*.jar app.jar
RUN echo "test..."
EXPOSE 8888
# CMD ["java","-jar","app.jar"]
CMD ["java","-jar","vehicle-management-project-0.0.2-SNAPSHOT.jar"]