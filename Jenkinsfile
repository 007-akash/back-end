pipeline {
    agent any

    stages { 
        stage('Config Service') {
            steps {
                dir('configserver-service') {
                    withMaven(maven : 'MAVEN_HOME')   {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Service Registry') {
            steps {
                dir('service-registry') {
                    withMaven(maven : 'MAVEN_HOME')   {
                        bat 'mvn clean install'
                    }
                }
            }
        }


        stage('Build and Test Flight MicroService') {
            steps {
                dir('flight-service') {
                    withMaven(maven : 'MAVEN_HOME')  {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Booking MicroService') {
            steps {
                dir('booking-service') {
                    withMaven(maven : 'MAVEN_HOME')   {
                        bat 'mvn clean install'
                    }
                }
            }
        }
        
        stage('Build and Test Payment MicroService') {
            steps {
                dir('payment-microservice') {
                   withMaven(maven : 'MAVEN_HOME')   {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('API Gateway') {
            steps {
                dir('api-gateway') {
                    withMaven(maven : 'MAVEN_HOME') {
                        bat 'mvn clean install'
                    }
                }
            }
        }
    }
    
}
