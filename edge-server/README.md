##SENIOR PLANNING

###FIRST STEPS
The project has three microservices:

- user-server
- activities-server
- center-server

Also, there is an edge-service that will communicate with all of them.

######ORDER OF EXECUTION
1. Grant privileges to your user in the database. Then, you have to execute the **final_proyect** script.
2. You have to execute the command ```mvn: spring-boot:run``` to start the services in this order: 
   1. eureka-server
   2. micro-services
   3. edge-server
   
3. Open finalproyectangular
4. Run ```npm i``` to install all dependencies. 
5. Run ```ng serve```. Navigate to http://localhost:4200/.

If you want to test the microservices, you have to execute the **final_proyect_test** script.

###ENJOY
This project is focused to manage elderly centers. There are two types of roles: workers(the personal of the center) and clients(older people registered in the center).
Workers can register users to the platform. Also, workers can add, edit or remove activities from their center. Clients have access to the activities of the center and they can registered in the activies.

Introduce the following credentials to enjoy the project:

If you want to be a worker:
- User: enrique01
- Password: 123456

If you want to be a user:
- User: julito
- Password: 123456






