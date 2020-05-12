pipeline {
    agent any

    stages {
       
        /* stage('Build package') { 
            steps {
                sh 'mvn -f future-traffic/pom.xml -DskipTests clean package' 
            }
        } 
        
        
        
        stage('Deploy on runtime') {
            steps {
                sshagent(credentials: ['deploy-key-rsa']) {
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker pull 192.168.160.99:5000/esp22-future-traffic"
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker run -d -p 22080:8080 --name esp22-futuretraffic esp22-future-traffic"
                }
            }
        }
        */
    
       

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
        stage('Deploy to Artifactory') { 
            steps {
                sh 'mvn deploy -f future-traffic/pom.xml -s settings.xml -DskipTests' 
            }
        }
    }
}