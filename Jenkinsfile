pipeline {
    agent any


    stages { 
        stage('Start Config Service') {
            steps {
                dir('configserver-service') {
                    script {
                        sh 'mvn spring-boot:run & echo $! > configserver-service.pid'
                        // Wait for the Config Server to be available
                        sleep(time: 10, unit: 'SECONDS')
                          
                    }
                }
            }
        }

        stage('Service Registry') {
            steps {
                dir('service-registry') {
                    script {
                        sh 'mvn clean install'
                    }
                }
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

        stage('API Gateway') {
            steps {
                dir('api-gateway') {
                    script {
                        sh 'mvn clean install'
                    }
                }
            }
        }
    }


    post {
        always {

                    script {
                        dir('configserver-service') {
                            // Stop the ConfigServer Service using the stored PID
                            sh 'kill $(cat configserver-service.pid)'
                        }
                    }
        }
        failure {
            echo 'Build or tests failed!'
        }
        success {
            echo 'Build and tests succeeded!'
        }
    }
}
