#!/bin/bash

cd "$(dirname "$0")/../zephyr"
./mvnw package -Dmaven.test.skip=true
java -jar target/zephyr-0.0.1-SNAPSHOT.jar
