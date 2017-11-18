FROM anapsix/alpine-java
MAINTAINER Sébastien Vermeille <sebastien.vermeille@gmail.com>
COPY bot-moe/build/libs/bot-moe-*-SNAPSHOT.jar /home/bot-moe.jar
CMD ["java","-jar","/home/bot-moe.jar"]