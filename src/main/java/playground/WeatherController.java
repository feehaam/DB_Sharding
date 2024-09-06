package playground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService clientMasterService;

    @GetMapping("/{clientdbName}")
    public Object findFromDatabase(@PathVariable String clientdbName) {
        var response = clientMasterService.getClientNames(clientdbName);
        LOGGER.info(response.toString());
        return response;
    }
}