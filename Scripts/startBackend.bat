@ECHO OFF
CD ..
CD zephyr
mvnw package
java target/zephyr-0.0.1-SNAPSHOT.jar
pause
cmd /k startBackend