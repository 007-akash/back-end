pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        CONFIG_SERVER_URL = 'http://host.docker.internal:8888'
    }

    stages { 
       
        stage('Fetch Configuration') {  
            steps {
                script {
                    // Fetch configuration from Config Server
                    def services = ["flight-service", "booking-service", "payment-service"]
                    services.each { service ->
                        sh "curl --verbose -o ${service}-config.json ${CONFIG_SERVER_URL}/${service}/default"
                    }
                    services.each { service ->
                        sh "ls -l ${service}-config.json"
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
                        dir('payment-service') {
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

                   echo 'Success'
        }
        failure {
            echo 'Build or tests failed!'
        }
        success {
            echo 'Build and tests succeeded!'
        }
    }
}
