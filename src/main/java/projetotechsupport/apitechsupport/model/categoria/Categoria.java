package projetotechsupport.apitechsupport.model.categoria;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.tag.Tag;

import java.util.List;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private List<GrupoAssignado> gruposAssignados;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tag;

}
