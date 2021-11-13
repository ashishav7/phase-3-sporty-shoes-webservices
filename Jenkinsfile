pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }
    stages {
        
        stage('Maven Compile') {
            steps {
                echo '----------------- This is a compile phase ----------'
                sh 'mvn clean compile'
            }
        }
        
         stage('Maven Test') {
            steps {
                echo '----------------- This is a compile phase ----------'
            }
        }
        
        stage('Maven Build') {
             steps {
                echo '----------------- This is a build phase ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- This is a build docker image phase ----------'
                sh '''
                    docker image build -t phase-3-sporty-shoes-webservices .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- This is a docker deploment phase ----------'
                sh '''
                 (if  [ $(docker ps -a | grep phase-3-sporty-shoes-webservices | cut -d " " -f1) ]; then \
                        echo $(docker rm -f phase-3-sporty-shoes-webservices); \
                        echo "---------------- successfully removed phase-3-sporty-shoes-webservices ----------------"
                     else \
                    echo OK; \
                 fi;);
            docker container run --restart always --name phase-3-sporty-shoes-webservices -p 8081:3000 -d phase-3-sporty-shoes-webservices
            '''
            }
        }
    }
}
