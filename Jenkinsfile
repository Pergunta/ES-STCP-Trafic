pipeline {
    agent any

    stages {
        stage('Test connection'){
            steps{
                sshagent(credentials: ['future-traffic-runtime']){
                    sh 'echo "Testing ssh connection..."'
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 uname -a"
                }
            }
        }

        stage('Deploy to Artifactory'){
            steps{
                sh 'cd future-traffic/ && mvn deploy -s ../settings.xml -DskipTests'
            }
        }

        stage('Deploy backend'){
            steps {
                dir('future-traffic') {
                    sh "docker build -t esp22-gateway ."
                    sh "docker tag esp22-gateway 192.168.160.99:5000/esp22-gateway"
                    sh "docker push 192.168.160.99:5000/esp22-gateway"
                }
            }
        }

        stage('Deploy Future Traffic App'){
            steps {
                dir('frontend') {
                    sh "docker build -t esp22-frontend ."
                    sh "docker tag esp22-frontend 192.168.160.99:5000/esp22-frontend"
                    sh "docker push 192.168.160.99:5000/esp22-frontend"
                }
            }
        }

        stage('Cucumber Tests'){
            steps{
                sh 'cd future-traffic/ && mvn test'
            }
        }

        stage('Deploy runtime'){
            steps{
                sshagent(credentials: ['future-traffic-runtime']){
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 curl -X GET http://192.168.160.99:8082/artifactory/libs-release-local/com/esp22/futureTraffic/0.0.1.1/futureTraffic-0.0.1.1.jar --output target/futureTraffic-0.0.1.1.jar"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker pull 192.168.160.99:5000/esp22-frontend:latest"

                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker stop esp22-gateway || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rm esp22-gateway || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker rmi 192.168.160.99:5000/esp22-gateway || true"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker pull 192.168.160.99:5000/esp22-gateway:latest"
                    
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run --name esp22-database -p 6106:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=buses -d mysql:latest"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run -d -p 6080:8888 --name esp22-gateway 192.168.160.99:5000/esp22-gateway"
                    sh "ssh -o 'StrictHostKeyChecking=no' -l esp22 192.168.160.103 docker run -d -p 6030:80 --name esp22-frontend 192.168.160.99:5000/esp22-frontend"
                }
            }
        }
    }
}
