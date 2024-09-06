package playground.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playground.db.config.WeatherRepository;
import playground.db.config.DBContextHolder;
import playground.entity.Region;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository temperatureRepository;

    public Object getClientNames(String client) {
        switch (client) {
            case "db1":
                DBContextHolder.setCurrentDb(Region.NORTHERN);
                break;
            case "db2":
                DBContextHolder.setCurrentDb(Region.SOUTHERN);
                break;
            case "db3":
                DBContextHolder.setCurrentDb(Region.PACIFIC);
                break;
        }
        return temperatureRepository.findAll();
    }
}