#!/bin/bash

echo "Compiling application..."
../sbt/bin/sbt assembly

# Directory where spark-submit is defined
# Install spark from https://spark.apache.org/downloads.html
SPARK_HOME=/Users/${USER}/spark-1.6.3-bin-hadoop2.6

# JAR containing a simple hello world
JARFILE=`pwd`/target/scala-2.10/SparkHelloWorld1.6.3-assembly-1.0.jar

# Run it locally
${SPARK_HOME}/bin/spark-submit --class HelloWorld --master local $JARFILE
