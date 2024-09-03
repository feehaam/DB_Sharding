package playground.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {
    // Define custom query methods if needed
}

