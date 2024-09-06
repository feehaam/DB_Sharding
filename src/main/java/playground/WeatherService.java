package playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository temperatureRepository;

    public Object getClientNames(String client) {
        switch (client) {
            case "db1":
                DBContextHolder.setCurrentDb(ClientNames.DB1);
                break;
            case "db2":
                DBContextHolder.setCurrentDb(ClientNames.DB2);
                break;
            case "db3":
                DBContextHolder.setCurrentDb(ClientNames.DB3);
                break;
        }
        return temperatureRepository.findAll();
    }
}