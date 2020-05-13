1. Run mysql inside a docker container LOCALLY:

> docker pull mysql:latest

> sudo docker run --name example -d -p3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql:latest

> sudo docker exec -it example /bin/bash

> mysql -uroot -ppassword

> CREATE DATABASE buses;

> USE buses;