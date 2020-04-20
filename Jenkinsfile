pipeline {
	agent any

    stages {
        stage('Gateway Test') {
            steps {
				dir('future-traffic') {
                	sh 'mvn test'
				}
            }
        }
        stage('Webserver Test') {
            steps {
				dir('future-traffic') {
                	sh 'mvn test'
				}
            }
        }
        stage('Build Gateway') {
            steps {
				dir('future-traffic') {
                    sh 'docker build -t esp22-gateway .'
                }
            }
        }
        stage('Build Webserver') {
            steps {
				dir('frontend') {
                    sh 'docker build -t esp22-webserver .'
                }
            }
        }
        stage('Publish Gateway') {
            steps {
				dir('future-traffic') {
                   sh '''
                        docker tag esp22-gateway 192.168.160.99:5000/esp22-gateway
                        docker push 192.168.160.99:5000/esp22-gateway
                    '''
                }
            }
        }
        stage('Publish Webserver') {
            steps {
				dir('frontend') {
                   sh '''
                        docker tag esp22-webserver 192.168.160.99:5000/esp22-webserver
                        docker push 192.168.160.99:5000/esp22-webserver
                    '''
                }
            }
        }
    }
}
