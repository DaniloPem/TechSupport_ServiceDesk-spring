package projetotechsupport.apitechsupport.model.usuario;

import java.util.List;

public record DadosVisualizacaoUsuario(
        Long id,
        String codigo,
        String nome,
        String email,
        String telefone,
        List<Long> gruposAssignadosId,
        List<String> gruposAssignadosNome,
        Boolean administrador
) {
    public DadosVisualizacaoUsuario(Usuario usuario) {
        this(usuario.getId(),
                usuario.getCodigo(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getIdGruposAssignados(),
                usuario.getNomeGruposAssignados(),
                usuario.isAdministrador());
    }
}
