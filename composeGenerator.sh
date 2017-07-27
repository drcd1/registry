#!/bin/bash
# Generates a docker-compose.yml

echo "version: '3'">docker-compose.yml
echo "services:">>docker-compose.yml

echo "">registry/replicas.java

for ((i = 1; i <= $1; i++));
do
	echo "app-"$i>>registry/replicas.txt
	
	echo "  app-"$i":">>docker-compose.yml
	echo "    build: ./app">>docker-compose.yml
	echo "    expose:">>docker-compose.yml
	echo "      - '5051'">>docker-compose.yml
	echo "      - '5052'">>docker-compose.yml
done

echo "">>docker-compose.yml
echo "  registry:">>docker-compose.yml
echo "    build: ./registry">>docker-compose.yml
echo "    expose:">>docker-compose.yml
echo "      - '5050'">>docker-compose.yml
