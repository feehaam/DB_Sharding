- ### The application stores & retrieves weather information across 3 different shards (SOUTHERN, NORTHERN, PACIFIC)
- ### Shards are implemented using different Database systems such as Northern -> Postgres DB 1, Southern -> Postgres BD 2, Pacific & Other (if added more) -> MySQL

### Follow the steps to run the application
- Run the docker-compose
- Create a database named `postgres1` in postgres server 1 and `postgres2` in postgres server 2 (pgAdmin container also added to manage DB)
- Then create a table `weather` using the following query in both databases.
````shell
CREATE TABLE IF NOT EXISTS weather (
        id SERIAL PRIMARY KEY,
        temperature_cel DOUBLE PRECISION NOT NULL,
        humidity_percentage INT NOT NULL,
        wind_kph DOUBLE PRECISION NOT NULL,
        date_time TIMESTAMP NOT NULL,
        region VARCHAR(255) NOT NULL
    );
````
- Similarly, create a database `mysql1` in mySQL server (phpMyAdmin container also added to manage DB)
- Then create a similar table `weather` using the following query
````shell
CREATE TABLE IF NOT EXISTS weather (
        id INT AUTO_INCREMENT PRIMARY KEY,
        temperature_cel DOUBLE NOT NULL,
        humidity_percentage INT NOT NULL,
        wind_kph DOUBLE NOT NULL,
        date_time DATETIME NOT NULL,
        region VARCHAR(255) NOT NULL
    );
````
- Now run the application and it's ready to go.
### Quick API note
- http://localhost:8080/weather/generate/{numberOfDummyEntry} -> generates random weather rows and stores those across different shards.
- http://localhost:8080/weather/get-by-region/{region} [regions: ``NORTHERN``/``SOUTHERN``/``PACIFIC``] -> returns all weather info from that region/shard