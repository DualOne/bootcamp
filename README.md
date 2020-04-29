Web service for operating with users tokens with extended token generation logic.
Provides two REST endpoints:
 * `POST /users/<username>/token/generate` - generate user token by its username provided in `<username>` path parameter
 * `POST /users/<username>/token/validate` - validate token provided in request body for user with a username provided in `<username>` path parameter

Request body example for token validation:
<pre>
{
    "token": "18126e7b-d3f8-3b3f-be4d-f094def5b7de1"
}
</pre>

Uses **Spring Web MVC** module for serving HTTP requests with builtin JSON-to-POJO serialization/deserialization,
which is configured via **Spring Boot** application configuration.
Also, uses service beans for storing token generation and validation business logic which are managed by corresponding **Spring Profiles**.
Introduces fully fledged MVC pattern in code organization.

Supports two token formats, which can be configured via application configuration file located in `src/main/resources/application.properties` file: 
 * numeric - set `spring.profiles.active` property value to `number-token`
 * alphanumeric - set `spring.profiles.active` property value to `string-token`
 
After setting preferred token format application should be built by using instructions from below.

To run this application source code should be packed as `war` archive,
which should be deployed to one of the **Java Applications Servers** (**Apache Tomcat** or other).

To build deployment artifact import this project as a **Gradle** project into **IDE** (for example, **IntelliJ IDEA**),
and run artifact build using `bootWar` Gradle task located in `build` task group.
Or simply run Gradle wrapper script by executing `gradlew` file (or `gradlew.bat` on Windows OS) with `bootWar` parameter.

After executing `bootWar` task corresponding archive file for deploying to Application Server
will be located by `build/libs` path relative to project root.
