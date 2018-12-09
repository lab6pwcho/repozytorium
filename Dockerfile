FROM java:8
COPY . /
WORKDIR /
RUN javac plik.java
CMD ["java", "-classpath", "mysql-connector-java-5.1.6.jar:.","plik"]
