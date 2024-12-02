
up:
	docker-compose up --build --force-recreate -d

stop:
	docker-compose stop

# Command to completely remove containers, volumes, and images
kill:
	docker-compose down --volumes --rmi all

# Command to show logs of services in real time
logs:
	docker-compose logs -f

refresh:
	docker-compose stop
	docker-compose up --build --force-recreate -d
