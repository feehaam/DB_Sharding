- ### The application stores & retrieves weather information across 3 different shards (SOUTHERN, NORTHERN, PACIFIC)
- ### Each shard is implemented using a different Database system (Northern -> Postgres, Southern -> MySQL, Pacific -> Mongo)

### Follow the steps to run the application
- Run the docker-compose
- Create a database named `postgres1` in postgres 
- Then create a table using the following query
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
- Similarly, create a database `mysql1` in mySQL container
- Then create a table using the following query
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
- http://localhost:8080/weather/generate/10 -> generates 10 random weather rows and stores those across different shards.
- http://localhost:8080/weather/get-by-region/northern or http://localhost:8080/weather/get-by-region/southern or http://localhost:8080/weather/get-by-region/pacific -> returns all temperature info from that region/shard