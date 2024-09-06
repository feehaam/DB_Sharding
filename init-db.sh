#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE ds_core;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "ds_core" <<-EOSQL
    CREATE TABLE IF NOT EXISTS entity1 (
        id INT NOT NULL PRIMARY KEY,
        entity1Name VARCHAR(255)
    );
EOSQL