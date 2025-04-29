

# CONNECT
Docker db connect from terminal.

```declarative

$ sudo apt install postgresql-client

$ docker ps
CONTAINER ID   IMAGE             COMMAND                  CREATED          STATUS          PORTS                                         NAMES
a9178d58f8e6   postgres:latest   "docker-entrypoint.s…"   14 minutes ago   Up 6 minutes    0.0.0.0:5000->5432/tcp, [::]:5000->5432/tcp   patient-service-db

$ psql -h localhost -p 5000 -U admin_user -d db

```