package playground;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.entity.Entity1;

@Repository
public interface ClientMasterRepository extends JpaRepository<Entity1, String> {
    Entity1 findByEntity1Name(String name);
}