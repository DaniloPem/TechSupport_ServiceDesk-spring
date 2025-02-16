package projetotechsupport.apitechsupport.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.ticket.Ticket;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subtag> subtags;

    private boolean ativo;

    public Tag(DadosCadastroTag dadosCadastroTag, List<Subtag> subtags) {
        this.nome = dadosCadastroTag.nome();
        this.subtags = subtags;
        this.ativo = true;
    }

    public List<String> getNomeSubTags() {return subtags == null ? null : subtags.stream().map(Subtag::getNome).toList();}
    public void desabilitar() {
        this.ativo = false;
    }
}
