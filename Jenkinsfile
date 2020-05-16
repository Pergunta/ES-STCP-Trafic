pipeline {
    agent any

    stages {
       
         stage('Build package') { 
            steps {
                sh 'mvn -f future-traffic/pom.xml -DskipTests clean package assembly:single' 
            }
        } 
        /*
        stage('Deploy to Artifactory') { 
            steps {
                sh 'mvn deploy -f future-traffic/pom.xml -s settings.xml' 
            }
        }
        */
        /*
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
        stage('Deploy db runtime') {
            steps {
                sshagent(['future-traffic-runtime']) {
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker stop esp22-database || true "
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rm esp22-database || true "
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run --name esp22-database -p 6106:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=buses -d mysql:latest "         
                }
            }
        }
        stage('Deploy backend runtime') {
            steps {
                sshagent(['future-traffic-runtime']) {
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker stop esp22-gateway || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rm esp22-gateway || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rmi 192.168.160.99:5000/esp22-gateway || true"
                   
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker pull 192.168.160.99:5000/esp22-gateway "
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run -d -p 6080:8888 --name esp22-gateway 192.168.160.99:5000/esp22-gateway"
                }
            }
        }
        stage('Deploy front-end runtime') {
            steps {
                sshagent(['future-traffic-runtime']) {
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker stop esp22-webserver || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rm esp22-webserver || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rmi 192.168.160.99:5000/esp22-webserver || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker pull 192.168.160.99:5000/esp22-webserver"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run -d -p 6030:80 --name esp22-webserver 192.168.160.99:5000/esp22-webserver"
                }
            }
        }
    }
    /*
pipeline {
    agent any

    stages {
        stage('Test'){
            steps{
                sshagent(credentials: ['esp22-ssh-key']){
                    sh 'echo "testing ssh connection"'
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 uname -a"
                }
            }
        }       
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
                sh 'mvn -f future-traffic/pom.xml -DskipTests clean package'
                sh 'mvn deploy -f future-traffic/pom.xml -s settings.xml -DskipTests' 
            }
        }
        stage('Deploy on runtime') {
            steps {
                sshagent(credentials: ['esp22-ssh-key']) {
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker pull 192.168.160.99:5000/esp22-gateway"
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker pull 192.168.160.99:5000/esp22-webserver"
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker run -d -p 22080:8080 --name esp22-backend esp22-gateway"
                    sh "ssh -o 'StrictHostKeyChecking=no' esp22@192.168.160.103 docker run -d -p 22081:8080 --name esp22-frontend esp22-webserver"
                }
            }
        }
    }
    */
}