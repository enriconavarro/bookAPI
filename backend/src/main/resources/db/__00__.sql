--- Execute primeiro essa linha

CREATE DATABASE book_api ENCODING='UTF8' LC_COLLATE='pt_BR.UTF-8' LC_CTYPE='pt_BR.UTF-8' TEMPLATE=template0;

--- Execute depois esse bloco

CREATE ROLE book_api LOGIN
  ENCRYPTED PASSWORD 'book_api'
  SUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

--- Execute dentro do database criado acima

CREATE SCHEMA book_api;

GRANT ALL ON SCHEMA book_api TO postgres;
GRANT USAGE ON SCHEMA book_api TO book_api;
ALTER SCHEMA book_api OWNER TO postgres;
