pipeline{
    agent any
    /*
    tools {
         maven 'maven'
         jdk 'java'
    }
    */
    stages{
        stage('checkout'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'github_ssh_calamza', url: 'https://github.com/calamza/holamundo.git']]])
            }
        }
        stage('build'){
            steps{
                script {
                    docker run -it --rm --name maven-node -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.3-jdk-8 mvn clean install

                }
               //bat 'mvn package'
            }
        }
    }
}