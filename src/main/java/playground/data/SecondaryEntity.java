package playground.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "secondary_table")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class SecondaryEntity {
    @Id
    private Integer id;
    private String des;
}