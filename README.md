# SW Card Game Test

This is a web application to provide a card came for players,


## Run the backend service
Step 1:
```
$ cd /path/to/sw-card-game-test
$ mvn clean install
```
Step 2
```
$ cd ./sw-ia-webflux
$ mvn spring-boot:run
```


## frontend setup
First of all cd to the frontend directory.
```
cd  <path>/sw-frontend-vue
```
Then install dependcency
```
npm install
```

 Compiles and hot-reloads for development
```
npm run serve
```

 Compiles and minifies for production
```
npm run build
```

 Lints and fixes files
```
npm run lint
```

#### Modules of Card Game

- #### sw-service-api <br>
     This module is divided out for other modules reuse of the APIs and DTO(data transfer object), especially for microservice such as Spring Cloud.
- #### sw-service-impl <br>
     This module provide the mainly service by register task to scheduler module that provide Card Play Room Management .<br>
     And publisher the last status of every room to Frontend.
- #### sw-ia-lb
     This module provide load balance for Card Game services.
- #### sw-ia-webfux
     Interactive with Frontend through websocket.
- #### sw-frontend-vue
     UI of Card Game and implement via VUE.
 
 Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



![architecture design](https://github.com/zack4coding/sw-card-game-test/blob/main/card-game-architecture.png)
