package projetotechsupport.apitechsupport.model.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.service.UsuarioService;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7)
    private String codigo;

    @Column(nullable = false, length = 50)
    private String nome;

    @Email
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(length = 20)
    private String telefone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_grupo_assignado",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupo_assignado_id"))
    private List<GrupoAssignado> gruposAssignados;

    private boolean administrador;

    public Usuario(DadosCadastroUsuario dadosUsuario, List<GrupoAssignado> gruposAssignados, String codigoUsuario) {
        this.codigo = codigoUsuario;
        this.nome = dadosUsuario.nome();
        this.email = dadosUsuario.email();
        this.telefone = dadosUsuario.telefone();
        this.gruposAssignados = gruposAssignados;
        this.administrador = dadosUsuario.administrador();
    }

    public List<String> getNomeGruposAssignados() { return gruposAssignados == null ? null : gruposAssignados.stream()
                    .map(GrupoAssignado::getNome).toList();
    }

    public List<Long> getIdGruposAssignados() { return gruposAssignados == null ? null : gruposAssignados.stream()
            .map(GrupoAssignado::getId).toList();
    }
}
