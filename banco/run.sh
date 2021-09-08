#!/bin/sh

javac src/Main.java
java -classpath .:src/model/mariadb-java-client-2.7.2.jar src.Main
find src/ -name "*.class" -exec rm -rf {} \;
