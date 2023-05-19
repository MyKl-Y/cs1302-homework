#!/bin/bash -ex

javac -d bin src/cs1302/hw/contract/Savable.java
javac -d bin -cp bin src/cs1302/hw/impl/TextBook.java
javac -d bin -cp bin src/cs1302/hw/impl/GameProgress.java
javac -d bin -cp bin src/cs1302/hw/impl/MovieTimestamps.java
javac -d bin -cp bin src/cs1302/hw/Driver.java

rm progressInfo.txt
rm progress0.txt
rm progress1.txt

ls -lh
java -cp bin cs1302.hw.Driver
ls -lh

check1302 src/
