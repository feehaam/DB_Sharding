#!/bin/bash
set -e

# Create the ds_core database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE ds_core;
EOSQL

# Connect to ds_core and create the weather table and insert data
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "ds_core" <<-EOSQL
    CREATE TABLE IF NOT EXISTS weather (
        id SERIAL PRIMARY KEY,
        temperature_cel DOUBLE PRECISION NOT NULL,
        humidity_percentage INT NOT NULL,
        wind_kph DOUBLE PRECISION NOT NULL,
        date_time TIMESTAMP NOT NULL,
        region VARCHAR(255) NOT NULL
    );

    INSERT INTO weather (temperature_cel, humidity_percentage, wind_kph, date_time, region)
    VALUES (22.5, 60, 15.2, '2023-10-10 14:30:00', 'NORTHERN');

    INSERT INTO weather (temperature_cel, humidity_percentage, wind_kph, date_time, region)
    VALUES (28.3, 70, 18.5, '2023-10-11 09:00:00', 'SOUTHERN');
EOSQL