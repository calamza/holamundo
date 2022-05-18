pipeline{
    agent any
    /*
    tools {
         maven 'maven'
         jdk 'java'
    }
    */
    stages{
        stage('Checkout'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'ssh_github_jtassi', url: 'git@github.com:calamza/holamundo.git']]])
            }
        }
        stage('Build artifact'){
            agent {
                label 'maven'
            }
            steps{
                sh '''
                    pwd
                    bat 'mvn package
                    #mvn clean install
                '''
                
               //bat 'mvn package'
            }
        }
    }
}