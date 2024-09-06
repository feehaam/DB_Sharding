package playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, String> {

}