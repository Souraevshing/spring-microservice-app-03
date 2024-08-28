### Microservice architecture including 3 services :

- Job Microservice
- Company Microservice
- Review Microservice

## Add the below code inside `docker-compose.yml` file to run postgres and pgadmin inside container in local machine :

`

    services:
        postgres:
            container_name: postgres_container
            image: postgres:16
            environment:
                POSTGRES_USER: codey
                POSTGRES_PASSWORD: admin
                PGDATA: /var/lib/postgresql/data
                POSTGRES_DB: job
            volumes:
                - postgres:/var/lib/postgresql/data
            ports:
                - "5432:5432"
            networks:
                - postgres
            restart: unless-stopped
        pgadmin:
            container_name: pgadmin_container
            image: dpage/pgadmin4:7.0
            environment:
                PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-isauravshing@gmail.com}
                PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}
                PGADMIN_CONFIG_SERVER_MODE: 'False'
            volumes:
                - pgadmin:/var/lib/pgadmin
            ports:
                - "5050:80"
            networks:
                - postgres
            restart: unless-stopped

    networks:
        postgres:
        driver: bridge

    volumes:
        postgres:
        pgadmin:


`