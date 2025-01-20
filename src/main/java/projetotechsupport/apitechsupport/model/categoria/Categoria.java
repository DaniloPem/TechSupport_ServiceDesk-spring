package projetotechsupport.apitechsupport.model.categoria;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.tag.Tag;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private List<GrupoAssignado> gruposAssignados;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tag;

    private boolean ativo;

    public Categoria(DadosCadastroCategoria dadosCadastroCategoria, List<Tag> tags) {
        this.nome = dadosCadastroCategoria.nome();
        this.tag = tags;
    }

    public List<String> getNomeGruposAssignados() { return gruposAssignados.stream().map(GrupoAssignado::getNome).toList();}
    public List<Long> getIdTag() { return tag == null ? null : tag.stream().map(Tag::getId).toList();}
    public List<String> getNomeTag() { return tag == null ? null : tag.stream().map(Tag::getNome).toList();}
    public void excluir() {
        this.ativo = false;
    }

}
