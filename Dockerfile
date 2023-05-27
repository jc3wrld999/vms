FROM java:8
EXPOSE 8888
CMD ["java","-jar","/build/libs/com.vehicle.management-0.0.2-SNAPSHOT.jar"]