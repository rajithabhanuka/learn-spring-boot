# learn-spring-boot
This spring boot tutorial to beginners and mid-level techies.
According to every topic I will create a branch, please pull relevant branch according to that.
Master Branch has all the topics which we cover in the future.

Please Note, I don't consider unit testing here, so I skip the test for every time

### Part 1 - Create Project Structure ###

branch name: project-structure-part-1
Maven Command: ```mvn clean install -DskipTests=true```

### Part 2 - Understand HTTP Methods ###

branch name: http-methods-part-2
Maven Command: ```mvn clean install -DskipTests=true```

#### post ####
```
curl --location --request POST 'http://localhost:8080/fruits' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "name" : "Berry",
    "country" : "USA",
    "qty" : 100
}'
```

#### get ####
```
curl --location --request GET 'http://localhost:8080/fruits'
```

```
curl --location --request GET 'http://localhost:8080/fruits/2'
```

```
curl --location --request GET 'http://localhost:8080/fruits/by-name?name=Berry'
```


#### put ####
```
curl --location --request PUT 'http://localhost:8080/fruits/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "name" : "Cherry",
    "country" : "Singapore",
    "qty" : 1010
}'
```

#### delete ####
```
curl --location --request DELETE 'http://localhost:8080/fruits/2'
```

### Part 3 - Validation ###

In this section you can see the payload validation before come in to the controller.

#### save ####

```
curl --location --request POST 'http://localhost:8080/fruits' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "name" : "Mango1",
    "country" : "US",
    "qty" : 100
}'
```

### Part 4 - Service Layer ###

### Part 5 - Response Handling ###

### Part 6 - MySql DB with JPA ###

### Part 7 - Dockerized ###

1. Create a network - ```docker network create spring-mysql-network```
2. Create a folder to mount the MySqlQL data, then we can protect data while restarting MySQL container or crashing the container
   ![MySQL Data Directory](/images/mysql_data_directory.PNG)
3. Run this command to create a MySQL container - ```docker run --name mysql-docker --network spring-mysql-network -v C://Users//rajit//MYSQL_DATA//MYSQL_CONFIG:/etc/mysql/conf.d --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=fruits_db" -v C://Users//rajit//MYSQL_DATA//DATA_DIR:/var/lib/mysql -d -p 3306:3306 mysql:8.0```
4. Go to the project root directory and run - ```mvn clean install -DskipTests=true```
5. After that run this command to build the docker image for our application - ```docker build -t fruit-app .```
6. Now run this command to create docker container for our application - ```docker run --network spring-mysql-network --name fruit-app-docker -p 8080:8080 -d fruit-app```
7. If you are not willing to create a network, then you can use this command to create docker container for our application - ```sudo docker run -t --link mysql-docker:mysql -p 8080:8080 fruit-app```

***Other Useful Commands***

- ```docker network ls``` - see all networks
- ```docker update --restart unless-stopped mysql-docker``` - start container automatically on restarts
- ```docker start mysql-docker``` - start a docker container
- ```docker stop mysql-docker``` stop a docker container
- ```docker rm mysql-docker``` remove a docker container
- ```docker rmi -f fruit-app``` remove an docker image
- ```docker ps -a``` - see all docker containers