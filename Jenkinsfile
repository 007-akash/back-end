pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        CONFIG_SERVER_URL = 'http://localhost:8888'
        SERVICES = ['flight-service', 'booking-service', 'payment-service']
    }

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
        stage('Fetch Configuration') {  
            steps {
                script {
                    // Fetch configuration from Config Server
                    SERVICES.each { service ->
                        sh "curl -o ${service}-config.json ${CONFIG_SERVER_URL}/${service}/default"
                    }
                }
            }
        }
        stage('Parallel Stages') {
            parallel {
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
                                sh 'mvn clean install -Dspring.config.location=flight-service-config.json'
                            }
                        }
                    }
                }
                
                stage('Build and Test Booking MicroService') {
                    steps {
                        dir('booking-service') {
                            script {
                                sh 'mvn clean install -Dspring.config.location=booking-service-config.json'
                            }
                        }
                    }
                }
                
                stage('Build and Test Payment MicroService') {
                    steps {
                        dir('payment-microservice') {
                            script {
                                sh 'mvn clean install -Dspring.config.location=payment-service-config.json'
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
        }
    }


    post {
        always {

                    script {
                        dir('configserver-service') {
                            // Stop the ConfigServer Service using the stored PID
                            sh '''
                    if [ -f configserver-service.pid ]; then
                        kill $(cat configserver-service.pid) && rm configserver-service.pid
                    fi
                    '''
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
