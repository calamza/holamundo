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
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'ssh_github_jtassi', url: 'git@github.com:calamza/holamundo.git']]])
            }
        }
        stage('build'){
            agent { label docker }
            steps{
                sh '''
                    echo "Antes de correr el docker"
                    bat 'mvn package
                    #mvn clean install
                '''
                
               //bat 'mvn package'
            }
        }
    }
}