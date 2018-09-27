package cz.creativeDock.slama.training.search.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Faq {

    @Id
    private Long id;
    //ToDo not just string
    @NotNull
    private String category;
    @NotNull
    private String question;
    @NotNull
    private String answer;

    public Faq(Long id, @NotNull String category, @NotNull String question, @NotNull String answer) {
        this.id = id;
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    public Faq(){}

    @Override
    public String toString() {
        return "Faq{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
