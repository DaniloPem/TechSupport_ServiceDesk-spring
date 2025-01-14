package projetotechsupport.apitechsupport.model.subtag;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.tag.Tag;

@Data
@Entity
public class Subtag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
