Web service for managing users and operating with users tokens with extended token generation logic.
Provides four REST endpoints:
 * `PUT /users` - create user with info provided in request body
 * `GET /users/<userId>` - retrieve user info by its id provided in `<userId>` path parameter
 * `POST /users/<userId>/token/generate` - generate user token by its id provided in `<userId>` path parameter
 * `POST /users/<userId>/token/validate` - validate token provided in request body for user with an id provided in `<userId>` path parameter

Request body example for user creation:
<pre>
{
    "username": "Mike"
}
</pre>
Request body example for token validation:
<pre>
{
    "token": "18126e7b-d3f8-3b3f-be4d-f094def5b7de1"
}
</pre>

Uses **Spring Web MVC** module for serving HTTP requests with builtin JSON-to-POJO serialization/deserialization,
which is configured via **Spring Boot** application configuration.
Also, uses service beans for storing token generation and validation business logic which are managed by corresponding **Spring Profiles**.
Code organized according to MVC pattern.

Introduces simple DB operations for persisting user data. Uses **Spring Data JPA** module based on **Hibernate ORM** for performing DB operations.
Adds DAO layer to MVC pattern to organize DB agnostic business entities' management.

As an example of DB configuration you can use **PostgreSQL DB** which should be installed locally (or somewhere else),
and properly configure application to work with your database. See `src/main/resources/application.properties` for DB configurations:
 * `spring.jpa.database=postgresql` - which DB will be used for persistence (change to appropriate DB name if some other DB will be used)
 * `spring.datasource.url=jdbc:postgresql://localhost:5432/postgres` - URL to locate your DB instance (for other databases URL structure can be different)
 * `spring.datasource.username=postgres` - username which will be used to connect to your DB instance
 * `spring.datasource.password=admin1111` - password for a user which will be used to connect to your DB instance 

Supports two token formats, which can be configured via application configuration file located in `src/main/resources/application.properties` file: 
 * numeric - set `spring.profiles.active` property value to `number-token`
 * alphanumeric - set `spring.profiles.active` property value to `string-token`
 
After setting preferred token format and DB configuration application should be built by using instructions from below.

To run this application source code should be packed as `war` archive,
which should be deployed to one of the **Java Applications Servers** (**Apache Tomcat** or other).

To build deployment artifact import this project as a **Gradle** project into **IDE** (for example, **IntelliJ IDEA**),
and run artifact build using `bootWar` Gradle task located in `build` task group.
Or simply run Gradle wrapper script by executing `gradlew` file (or `gradlew.bat` on Windows OS) with `bootWar` parameter.

After executing `bootWar` task corresponding archive file for deploying to Application Server
will be located by `build/libs` path relative to project root. 
