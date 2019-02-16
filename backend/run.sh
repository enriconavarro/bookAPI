#!/usr/bin/env bash

if [ "$1" == "debug" ]; then
    mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=7778"
else
    mvn spring-boot:run
fi