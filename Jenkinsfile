pipeline {
    agent any


    stages { 
        stage('Start Config Service') {
            steps {
                dir('configserver-service') {
                    script {
                        bat 'mvn spring-boot:run & echo $! > configserver-service.pid'
                        // Wait for the Config S erver to be available
                        retry(5) {
                            sleep(time: 10, unit: 'SECONDS')
                            bat 'curl --fail http://localhost:8888/actuator/health'
                        }
                    }
                }
            }
        }

        stage('Service Registry') {
            steps {
                dir('service-registry') {
                    script {
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
                    script {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Payment MicroService') {
            steps {
                dir('payment-microservice') {
                    script {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('API Gateway') {
            steps {
                dir('api-gateway') {
                    script {
                        bat 'mvn clean install'
                    }
                }
            }
        }
    }


    post {
        always {
            stage('Stop Config Server') {
                steps {
                    script {
                        dir('configserver-service') {
                            // Stop the ConfigServer Service using the stored PID
                            bat 'kill $(cat configserver-service.pid)'
                        }
                    }
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
