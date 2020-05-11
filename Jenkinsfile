pipeline {
    agent any

    stages {
        stage('Publish Gateway') {
            steps {
                dir('future-traffic') {
                    sh "docker build -t esp22-gateway ."
                    sh "docker tag esp22-gateway 192.168.160.99:5000/esp22-gateway"
                    sh "docker push 192.168.160.99:5000/esp22-gateway"
                }
            }
        }
        stage('Publish Webserver') {
            steps {
                dir('frontend') {
                    sh "docker build -t esp22-webserver ."
                    sh "docker tag esp22-webserver 192.168.160.99:5000/esp22-webserver"
                    sh "docker push 192.168.160.99:5000/esp22-webserver"
                }
            }
        }
    }
}