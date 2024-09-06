package playground.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import playground.entity.Weather;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService clientMasterService;

    @GetMapping("/get-by-region/{regionName}")
    public List<Weather> findFromDatabase(@PathVariable String regionName) {
        var response = clientMasterService.getByRegion(regionName);
        LOGGER.info(response.toString());
        return response;
    }

    @GetMapping("/generate/{count}")
    public String generate(@PathVariable Integer count) {
        return clientMasterService.generate(count);
    }

    @PostMapping
    public Weather create(@RequestBody Weather weather) {
        var response = clientMasterService.create(weather);
        LOGGER.info(response.toString());
        return response;
    }
}