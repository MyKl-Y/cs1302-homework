#1/bin/bash -x

javac -d bin src/cs1302/hw06/Email.java
javac -d bin -cp bin src/cs1302/hw06/LambdaFun.java

java -cp bin cs1302.hw06.LambdaFun

check1302 src
