pipeline {
    agent any

    stages { 
        stage('Config Service') {
            steps {
                dir('configserver-service') {
                    script  {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Service Registry') {
            steps {
                dir('service-registry') {
                    script  {
                        bat 'mvn clean install'
                    }
                }
            }
        }


        stage('Build and Test Flight MicroService') {
            steps {
                dir('flight-service') {
                    script {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Booking MicroService') {
            steps {
                dir('booking-service') {
                    script  {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Payment MicroService') {
            steps {
                dir('payment-microservice') {
                   script  {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('API Gateway') {
            steps {
                dir('api-gateway') {
                    script{
                        bat 'mvn clean install'
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
