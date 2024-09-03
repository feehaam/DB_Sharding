package playground.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
    // Define custom query methods if needed
}
