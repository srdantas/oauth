#!/bin/bash

./gradlew clean build -x test

docker build -t oauth .
docker tag oauth:latest 145156591738.dkr.ecr.sa-east-1.amazonaws.com/oauth:latest
docker push 145156591738.dkr.ecr.sa-east-1.amazonaws.com/oauth:latest

kubectl apply -f deploy/mongodb/deployment.yaml
kubectl apply -f deploy/mongodb/service.yaml

kubectl apply -f deploy/deployment.yaml
kubectl apply -f deploy/service.yaml
