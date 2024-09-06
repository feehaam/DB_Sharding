package playground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "entity1")
public class Entity1 implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "entity1Name")
    private String entity1Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntity1Name() {
        return entity1Name;
    }

    public void setEntity1Name(String entity1Name) {
        this.entity1Name = entity1Name;
    }
}