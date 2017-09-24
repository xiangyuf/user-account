#!/bin/bash

PID=$(docker ps | grep user-account | awk '{ print $1 }')

case $1 in
    "start")
        mvn clean package
        docker build --rm -t user-account .
        if [ -z "$PID" ]
        then
            docker rm user-account
            docker run -d -p 8080:8080 -p 8081:8081 --name=user-account user-account java -jar user-account-1.0-SNAPSHOT.jar
        fi
        echo Application is running
    ;;
    "stop")
        if [ -z "$PID" ]
        then
            echo Application is already stopped
        else
            docker stop user-account
        fi
    ;;
    "restart")
        docker restart user-account
    ;;
    "status")
        if [ -z "$PID" ]
        then
            echo Application is already stopped
        else
            echo Application is running
        fi
    ;;
    *)
      echo "Usage: $0 {build|start|stop|restart|status}"; exit 1;
    ;;
esac
