package projetotechsupport.apitechsupport.model.usuario;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;

import java.util.List;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codigo;

    private String nome;

    private String email;

    private String telefone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_grupo_assignado",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupo_assignado_id"))
    private List<GrupoAssignado> gruposAssignados;

}
