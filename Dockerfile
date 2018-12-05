FROM java:8
RUN apt-get update
RUN apt-get install -y apache2
CMD 
