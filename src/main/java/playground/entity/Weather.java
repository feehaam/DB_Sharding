package playground.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "temperature_cel", nullable = false)
    private double temperature;

    @Column(name = "humidity_percentage", nullable = false)
    private int humidity;

    @Column(name = "wind_kph", nullable = false)
    private double wind;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime localDateTime;

    @Column(name = "region", nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    public Weather() {

    }

    public Weather(double temperature, int humidity, double wind, LocalDateTime localDateTime, Region region) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.localDateTime = localDateTime;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id == weather.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", localDateTime=" + localDateTime +
                ", region=" + region +
                '}';
    }
}