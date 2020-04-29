Simple web service for token generation implemented by using plain **Java Servlets** which provides HTTP endpoints:
 * `GET /user/token/generate?user=<username>` - generate user token by its username provided in `<username>` request parameter

To run this application source code should be packed as `war` archive,
which should be deployed to one of the **Java Applications Servers** (**Apache Tomcat** or other).

To build deployment artifact import this project as a **Gradle** project into **IDE** (for example, **IntelliJ IDEA**),
and run artifact build using `war` Gradle task located in <code>build</code> task group.
Or simply run Gradle wrapper script by executing `gradlew` file (or `gradlew.bat` on Windows OS) with `war` parameter.

After executing `war` task corresponding archive file for deploying to Application Server
will be located by `build/libs` path relative to project root.