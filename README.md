# Desafio back-end spring-boot UDS Pizzaria

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/marcioper/uds-pizzaria-spring-backend.git
```

**2. Create Mysql database**
```bash
create database pizzaria
```
```
Backup database MySQL in root project: **pizzaria_2019-08-19.sql**
``

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

Collections Postman for tests in root project: **UDS.desafio.postman_collection.json**

The app defines following CRUD APIs.

    GET /api/v1/orders
    
    POST /api/v1/orders
    
    json:
	{
		"user": { 
			"id": 4 
		},
		"product": { 
			"id": 1 
		},
		"size": {
	   		"id": 3 
		},
		"flavor": {
	   		"id": 1 
		},
		"additionals": [
	   		{
	   			"id": 1
	   		},
	   		{
	   			"id": 3
	   		}
	   	]
	}
	
	result:
	{
	    "id": 6,
	    "user": {
	        "id": 4,
	        "name": "Teste",
	        "email": "teste@email.com",
	        "phone": "(111) 111-1111"
	    },
	    "product": {
	        "id": 1,
	        "name": "Pizza"
	    },
	    "size": {
	        "id": 3,
	        "name": "Grande",
	        "price": 40,
	        "preparation_time": 25
	    },
	    "flavor": {
	        "id": 1,
	        "name": "Calabresa",
	        "preparation_time": 0
	    },
	    "additionals": [
	        {
	            "id": 1,
	            "name": "Extra bacon",
	            "price": 3,
	            "preparation_time": 0
	        },
	        {
	            "id": 3,
	            "name": "Borda recheada",
	            "price": 5,
	            "preparation_time": 5
	        }
	    ],
	    "price_total": 48,
	    "preparation_time": 30,
	    "createdAt": null
	}
    
    GET /api/v1/orders/{userId}


