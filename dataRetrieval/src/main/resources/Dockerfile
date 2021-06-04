FROM postgres
ENV POSTGRES_USER p2g3
ENV POSTGRES_PASSWORD p2g3
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432