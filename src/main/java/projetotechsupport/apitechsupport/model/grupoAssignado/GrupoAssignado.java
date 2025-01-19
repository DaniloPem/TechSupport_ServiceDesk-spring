package projetotechsupport.apitechsupport.model.grupoAssignado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class GrupoAssignado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categoria_grupo_assignado",
                joinColumns = @JoinColumn(name = "grupo_assignado_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "gruposAssignados", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    public GrupoAssignado(DadosCadastroGrupoAssignado dadosCadastroGrupoAssignado, List<Categoria> categorias) {
        this.nome = dadosCadastroGrupoAssignado.nome();
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoAssignado that = (GrupoAssignado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<String> getNomeCategorias() { return categorias.stream().map(Categoria::getNome).toList();}

    public List<String> getNomeUsuarios() { return usuarios.stream().map(Usuario::getNome).toList();}

}
