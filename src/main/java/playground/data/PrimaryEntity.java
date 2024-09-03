package playground.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "primary_table")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PrimaryEntity {
    @Id
    private Integer id;
    private String name;
}