package projetotechsupport.apitechsupport.model.categoria;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.tag.Tag;

import java.util.List;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private List<GrupoAssignado> gruposAsignados;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tag;

}
