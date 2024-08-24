pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages { 

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
                        dir('payment-service') {
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
        }
    }


    post {
        always {

            echo 'Build Success'
        }
        failure {
            echo 'Build or tests failed!'
        }
        success {
            echo 'Build and tests succeeded!'
        }
    }
}
