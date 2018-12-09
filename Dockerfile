FROM java:latest  
COPY . /
WORKDIR /  
RUN javac javabaza.java
CMD ["java", "-classpath", "mysql-connector-java-8.0.13.jar:.","javabaza"]
