package projetotechsupport.apitechsupport.model.grupoAsignado;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.util.List;
import java.util.Objects;

@Data
@Entity
public class GrupoAssignado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario-grupoAsignado",
                joinColumns = @JoinColumn(name = "grupo_asignado_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "gruposAsignados", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;


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

}
