# 📊 Exchange Rate API – Evaluación Técnica

Este proyecto es una API REST reactiva desarrollada con Spring Boot + WebFlux, que permite registrar tipos de cambio entre monedas, convertir montos entre divisas, proteger los endpoints con JWT (JSON Web Token), y auditar automáticamente cada conversión con el usuario autenticado.

Tecnologías utilizadas: Java 17, Spring Boot 3.5.3, Spring WebFlux, Spring Security con JWT, Spring Data R2DBC con H2 Database, Maven y Lombok.

Cómo ejecutar el proyecto:

Clona el repositorio:

git clone https://github.com/carlos91193/exchange-rate-api.git  
cd exchange-rate-api

Ejecutar la aplicación:

mvn clean install  
mvn spring-boot:run

La aplicación estará disponible en http://localhost:8080

Autenticación (JWT):  
Endpoint: POST /auth/login  
Credenciales predeterminadas:

{
"username": "admin",
"password": "1234"
}

Respuesta esperada:

{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

Incluir este token en las solicitudes protegidas usando el header:

Authorization: Bearer <token>

Endpoints disponibles:

POST /api/exchange/convert
Request:

{
"sourceCurrency": "USD",
"targetCurrency": "PEN",
"amount": 100
}

Response:

{
"convertedAmount": 380.0,
"rate": 3.8,
"sourceCurrency": "USD",
"targetCurrency": "PEN"
}

POST /api/exchange/rate  
Request:

{
"sourceCurrency": "USD",
"targetCurrency": "PEN",
"rate": 3.80
}

GET /api/exchange/rates  
Devuelve todas las tasas de cambio registradas.

GET /api/exchange/logs  
Devuelve el historial de conversiones con auditoría. Ejemplo:

[
{
"username": "admin",
"sourceCurrency": "USD",
"targetCurrency": "PEN",
"originalAmount": 100,
"convertedAmount": 380,
"timestamp": "2025-06-24T22:10:00"
}
]

Base de datos:  
Se utiliza H2 en memoria vía R2DBC.  
Consola disponible en http://localhost:8080/h2-console  
JDBC URL: r2dbc:h2:mem:///exchangeDb  
Usuario: sa  
Contraseña: (vacío)

Estructura del proyecto:

src/main/java  
├── config/  
├── controller/  
├── dto/  
├── entity/  
├── repository/  
├── security/  
├── service/  
└── exception/

Constantes configurables:  
Las credenciales se encuentran en ApplicationConstants.java:

public static final String USERNAME = "admin";  
public static final String PASSWORD = "1234";

http://localhost:8080/swagger-ui.html

Consideraciones finales:  
Todos los endpoints están protegidos con JWT excepto /auth/login.  
Cada conversión es auditada.  
API construida completamente con programación reactiva.  
Ideal como base para sistemas modernos y microservicios.

Contacto:  
Carlos Cruzado  
Evaluación técnica para Alfin Banco – Grupo Coril  
cruzado_unt@hotmail.com# exchange-rate-api
