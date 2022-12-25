# MicroServices : Hello World !
### Basics of microservices architecture w/ Spring Cloud
The following project aims to create two single projects depending on each other as a first step, and then the purpose is to have an application with many services communicating following the microservices' architecture.

While READingME, you will find the steps that I followed during the creation till the containerisation of those microservices.
1. Create two services : Customer & Billing services
    The two Spring Boot applications are generally basics. Each microservice has its DAO, business and web layers. The structure of those projects is represented like the following :
   ![Customer-service_structure](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/structure_customer.png)
![Billing-service_structure](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/structure_billing.png)

2. Test the APIs using SWAGGER3 (Open API)
    As usual, after the creation of the multiple layers of a Spring Application, the next step is to test the connection to the APIs through several tools. In this case, we will use Swagger3 as an Open API Documentation, and try to check if the HTTP methods works on successfully.

![Customer-API](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/swagger-customer.png)

![Billing-API](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/swagger-billing.png)
   
Let's test those APIs by executing one of the methods. For the customer API, I will add new customer named "C03". And I will show the invoices for the customers.
![Customer-testAPI](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/testapi_customer.png)
![Billing-testAPI](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/testapi_billing.png)


3. Communication between microservices by OpenFeign

    In this project, we have two microservices communicating with each other. More specifically, the billing service needs more pieces of information from the customer service in order to identify the customer who the bill belongs for him. That's why, we have to use Spring Cloud OpenFeign as a tool to make web services easier. How can we use OpenFeign ? is by creating a REST client interface which we could specify the services that we would to consume.In our case, we will create a REST client interface for the customer service to receive the customers.

![OpenFeignInterface](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/openfeign.png)

4. Spring Cloud Gateway
    The gateway in Spring Cloud used as proxy between the users and the microservices. Instead of knowing ho to access to the services, the Spring Cloud Gateway facilitates the access by entering the service name in the URL instead of its address. It's configurable only once for the whole project and used for all the microservices of the project.  
   ![Gateway](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/gateway.png)

5. Eureka Service
    And for Registry Eureka Service, is used in order to hold the information about all client-service applications. a REST based service for locating services for the purpose of load balancing and failover of middle-tier servers.
   ![Eureka](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/eureka.png)


6. Containerize microservices using Docker
Thanks to the containerisation in Docker, we will never struggle with the 4 running instances of IntelliJ projects at the same time. The creation of containers deploying microservices facilitates the use of such a huge architecture. And when we talk about microservices means multiple applications then several containers, so we have to use docker compose in order to configure all the services through its configuration. So, the configuration of the microservices is defined in [docker-compose.yaml](https://github.com/loubnaAminou/MicroServices/blob/main/docker-compose/docker-compose.yaml). And as a result, we have 4 containers for 4 microservices :
   ![docker-containers](https://github.com/loubnaAminou/MicroServices/blob/main/imgs/docker-containers.png)
