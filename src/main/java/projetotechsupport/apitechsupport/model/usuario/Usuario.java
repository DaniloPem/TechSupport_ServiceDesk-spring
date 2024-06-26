package projetotechsupport.apitechsupport.model.usuario;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;

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
    @JoinTable(name = "usuario-grupoAsignado",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupoAsignado_id"))
    private List<GrupoAsignado> gruposAsignados;

}
