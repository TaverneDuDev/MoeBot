Moe Bot
============================================

[![Build Status](https://travis-ci.org/TaverneDuDev/MoeBot.svg?branch=master)](https://travis-ci.org/TaverneDuDev/MoeBot)
[![Sonar quality gate status](https://sonarcloud.io/api/badges/gate?key=fr.tavernedudev:bot-moe)](https://sonarcloud.io/dashboard?id=fr.tavernedudev%3Abot-moe)




A free gift written in Java for TaverneDuDev community

## Features

* Prevent slack user from adding something else than an url in a
specific channel
* Automatically add :+1: :-1: reactions to new messages of this channel

## Todo

* Count amount of like dislike and automatically remove the message if a specified ratio is reached.
* Improve test coverage

## Authors
Sébastien Vermeille <sebastien.vermeille@gmail.com> (maintainer)

## Prerequisites

* Java8

## Development (dev)

Create this file `bot-moe/src/main/resources/application-dev.properties`
Copy the content of application.properties but fill up the variables.

Launch locally:
> gradlew bootRun -Dspring.profiles.active=dev

**Note:** this file is ignored from git

## Deployment (prod)

Create this file `bot-moe/src/main/resources/application-prod.properties`

Copy the content of application.properties but fill up the variables.

Build executable jar:
> gradlew bootJar -Dspring.profiles.active=prod

Run:
> java -jar /bot-moe/build/libs/bot-moe-*.jar

Tadaa