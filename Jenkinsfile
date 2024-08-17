pipeline {
    agent any
    
    environment {
        JAVA_HOME = tool name: 'JDK 17', type: 'jdk'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git
                git 'https://your-git-repo-url.git'
            }
        }
        
        stage('Build and Test Flight MicroService') {
            steps {
                dir('flight-service') {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Booking MicroService') {
            steps {
                dir('booking-service') {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Payment MicroService') {
            steps {
                dir('payment-microservice') {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }
    }
    
    post {
        always {
            // Archive the test results and any other relevant artifacts
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            junit '**/target/surefire-reports/*.xml'
        }
        failure {
            echo 'Build or tests failed!'
        }
        success {
            echo 'Build and tests succeeded!'
        }
    }
}
