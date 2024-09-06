package playground.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playground.db.config.DBContextHolder;
import playground.entity.Region;
import playground.entity.Weather;
import playground.repository.WeatherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<Weather> getByRegion(String region) {
        DBContextHolder.determineShard(region);
        return weatherRepository.findAll();
    }

    public Weather create(Weather weather) {
        DBContextHolder.determineShard(weather.getRegion());
        return weatherRepository.save(weather);
    }

    private final Random random = new Random();
    private Double getRandomDoubleWithinRange(double range) {
        var val = (double) (random.nextInt((int) range * 100));
        return val / 100;
    }

    public String generate(Integer count) {
        for(int i=0; i<count; i++) {
            Weather weather = new Weather(
                    getRandomDoubleWithinRange(42),
                    random.nextInt(100),
                    random.nextInt(150),
                    LocalDateTime.now().minusDays(random.nextInt(700)),
                    random.nextInt() % 3 == 0 ? Region.NORTHERN :
                            random.nextInt() % 2 == 0 ? Region.SOUTHERN : Region.PACIFIC
            );
            DBContextHolder.determineShard(weather.getRegion());
            weatherRepository.save(weather);
        }
        return "Saved all";
    }
}
