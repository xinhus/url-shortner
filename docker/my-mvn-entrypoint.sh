#! /bin/bash -eu

mvn clean install -e -f  /usr/src/mymaven
java -jar target/url-shortner.jar
