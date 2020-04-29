Web service for operating with users tokens with extended token generation logic.
Uses simple Java Servlets for serving HTTP requests, and provides two HTTP endpoints:
 * <code>GET /users/token/generate?user=\<username\></code> - generate user token by its username provided in <code>\<username\></code> request parameter
 * <code>GET /users/token/validate?user=\<username\>&token=\<token\></code> - validate token provided in \<token\> request parameter for user with username provided in <code>\<username\></code> request parameter

Supports two token formats, which can be configured via application configuration file located in <code>src/main/resources/application.properties</code> file: 
 * numeric - set <code>tokenMode</code> property value to <code>NUMBER</code>
 * alphanumeric - set <code>tokenMode</code> property value to <code>STRING</code>

After setting preferred token format application should be built by using instructions from below.

To run this application source code should be packed as <code>war</code> archive,
which should be deployed to one of the Java Applications Servers (Apache Tomcat or other).

To build deployment artifact import this project as a Gradle project into IDE (for example, IntelliJ IDEA),
and run artifact build using <code>war</code> Gradle task located in <code>build</code> task group.
Or simply run Gradle wrapper script by executing <code>gradlew</code> file (or <code>gradlew.bat</code> on Windows OS) 
with <code>war</code> parameter.

After executing <code>war</code> task corresponding archive file for deploying to Application Server
will be located by <code>build/libs</code> path relative to project root.
 