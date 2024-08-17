pipeline {
    agent any
    
    tools {
        // Use the Maven installed on your machine
        maven 'Maven'
    }

    stages { 
        stage('Config Service') {
            steps {
                dir('configserver-service') {
                    withMaven(maven : 'M2_HOME')  {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Service Registry') {
            steps {
                dir('service-registry') {
                    withMaven(maven : 'M2_HOME')  {
                        bat 'mvn clean install'
                    }
                }
            }
        }


        stage('Build and Test Flight MicroService') {
            steps {
                dir('flight-service') {
                    withMaven(maven : 'M2_HOME') {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Booking MicroService') {
            steps {
                dir('booking-service') {
                    withMaven(maven : 'M2_HOME')  {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Payment MicroService') {
            steps {
                dir('payment-microservice') {
                   withMaven(maven : 'M2_HOME')  {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('API Gateway') {
            steps {
                dir('api-gateway') {
                    withMaven(maven : 'M2_HOME')  {
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
