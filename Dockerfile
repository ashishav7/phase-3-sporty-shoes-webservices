FROM openjdk:8-jdk
COPY ./target/phase-3-sporty-shoes-webservices-0.0.1-SNAPSHOT.jar phase-3-sporty-shoes-webservices-0.0.1-SNAPSHOT.jar
CMD ["java" ,"-jar","phase-3-sporty-shoes-webservices-0.0.1-SNAPSHOT.jar"]
RUN echo "jenkins ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
