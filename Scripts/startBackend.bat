@ECHO OFF
CD ..
CD zephyr
CALL mvnw package
java -jar target/zephyr-0.0.1-SNAPSHOT.jar
pause
