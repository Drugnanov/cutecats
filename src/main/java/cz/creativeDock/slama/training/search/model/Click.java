package cz.creativeDock.slama.training.search.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//ToDo nefunguje lombok????
@Data
@Entity
public class Click {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String category;
    @NotNull
    private Long count;

    public Click(@NotNull String category, @NotNull Long count) {
        this.category = category;
        this.count = count;
    }

    public Click() {
    }

    @Override
    public String toString() {
        return "Click{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
