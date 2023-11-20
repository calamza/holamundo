pipeline{
    agent {
         label 'docker'
    }
    /*
    tools {
         maven 'maven 3.6'
         jdk 'java'
    }
    */
    environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running. 'nexus-3' is defined in the docker-compose file
        NEXUS_URL = "192.168.42.131:8081"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "maven-releases"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexus"
        DOCKER_VERSION = "0.2.2"
        
        // Workfolder
        //WORKFOLDER = "/usr/jenkins/node_agent/workspace"
    }

    stages{
        stage('Checkout'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'git@github.com:calamza/holamundo.git']]])
            }
        }
        stage('Download artifact from nexus'){
            agent {
                label 'docker'
            }
            steps{
                sh '''
                    pwd 
                    curl -v -u admin:hola1234 -o app.jar http://192.168.42.131:8081/repository/maven-releases/org/springframework/Jenkins-holamundo/0.2.2/Jenkins-holamundo-0.2.2.jar
                '''
            }
        }
        stage('Build container'){
            agent {
                label 'docker'
            }
            steps{
                sh '''
                    docker build -t holamundo .
                    docker tag holamundo:latest 192.168.42.131:8082/holamundo:latest
                '''

            }
        } //fin stage build container
        stage('Upload container'){
            agent {
                label 'docker'
            }
            steps{
                sh '''
                    docker login -u admin -p hola1234 192.168.42.131:8082
                    docker push 192.168.42.131:8082/holamundo:latest
                '''

            }
        } //fin stage build container
        stage('Deploy container'){
            agent {
                label 'docker'
            }
            steps{
                sh '''
                    docker run -d -p 8085:80 holamundo
                '''

            }
        } //fin stage build container
        
        stage("Post") {
            agent {
                label 'docker'
            }
            steps {
                sh '''
                    pwd
                    echo "Clean up workfolder"
                    rm -Rf *
                '''
            }
        } //fin stage post
        
    }
}
