[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Java Spring Data

Continuous integration status:

[![Build Status](https://travis-ci.org/valandro/java-auth-spring.svg?branch=master)](https://travis-ci.org/valandro/java-auth-spring.svg?branch=master)
[![codecov](https://codecov.io/gh/valandro/java-auth-spring/branch/master/graph/badge.svg)](https://codecov.io/gh/valandro/java-auth-spring)


This project consists in an Authentication Service, using Jwts for generate JSON Web Tokens.

### Usage

For consumes the API it's simple:

**POST** /auth

REQUEST BODY
```json
{
    "username": "some-user",
    "password": "some-pass"
}
```

If your credentials are correct

RESPONSE BODY
```json
{  
   "token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJjbGllbnRJZFwiOjEsXCJhY2Nlc",
   "user_name":"Lucas",
   "access_level":1
}
```

And if is not

```json
{
    "httpStatus":404,
    "message":"Usuário não encontrado."
}
```


### Sequence Diagram

![Diagram](/diagram/diagram.png)

### Data model
The database used in this project has the following structure:

| COLUMNS     | TYPE        | EXTRA     |
| ------------| ------------| ----------|
| id          | INT(11)     | AI, PK, NN|
| id_client   | INT(11)     | NN        |
| name        | VARCHAR(255)| NN        |
| password    | VARCHAR(255)| NN        |
| access_level| INT(11)     | NN        |
| blocked     | INT(11)     | NN        |

### Environment
 - Java JDK 10
 - Gradle 4.3 or higher 
 
### License
MIT License. [Click here for more information.](LICENSE)
