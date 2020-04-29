Web service for operating with users tokens with extended token generation logic.
Uses simple **Java Servlets** for serving HTTP requests, and provides two HTTP endpoints:
 * `GET /users/token/generate?user=<username>` - generate user token by its username provided in `<username>` request parameter
 * `GET /users/token/validate?user=<username>&token=<token>` - validate token provided in `<token>` request parameter for user with a username provided in `<username>` request parameter

Supports two token formats, which can be configured via application configuration file located in `src/main/resources/application.properties` file: 
 * numeric - set `tokenMode` property value to `NUMBER`
 * alphanumeric - set `tokenMode` property value to `STRING`

After setting preferred token format application should be built by using instructions from below.

To run this application source code should be packed as `war` archive,
which should be deployed to one of the **Java Applications Servers** (**Apache Tomcat** or other).

To build deployment artifact import this project as a **Gradle** project into **IDE** (for example, **IntelliJ IDEA**),
and run artifact build using `war` Gradle task located in `build` task group.
Or simply run Gradle wrapper script by executing `gradlew` file (or `gradlew.bat` on Windows OS) with `war` parameter.

After executing `war` task corresponding archive file for deploying to Application Server
will be located by `build/libs` path relative to project root.
 