pipeline {
	agent any

    stages {
        stage('Build database'){
            steps{
                dir('future-traffic'){

                    sh 'docker pull mysql:latest'
                }
            }
        }
        stage('Build package') { 
            steps {
                sh 'mvn -f future-traffic/pom.xml -DskipTests clean package' 
            }
        }
        stage('Deploy to Artifactory') { 
            steps {
                sh 'mvn deploy -f future-traffic/pom.xml -s settings.xml -DskipTests' 
            }
        }


        /*stage('Build Gateway') {
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
        }*/
    }
}
