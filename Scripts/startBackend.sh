 #!/bin/bash

CD ..
CD zephyr
CALL mvnw package -Dmaven.test.skip=true
java -jar target/zephyr-0.0.1-SNAPSHOT.jar
pause