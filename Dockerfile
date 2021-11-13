FROM openjdk:8-jdk
COPY ./target/project-phase-3-0.0.1-SNAPSHOT.jar project-phase-3-0.0.1-SNAPSHOT.jar
CMD ["java" ,"-jar","project-phase-3-0.0.1-SNAPSHOT.jar"]
RUN echo "jenkins ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
