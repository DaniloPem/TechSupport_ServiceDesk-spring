package projetotechsupport.apitechsupport.model.usuario;

import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;

import java.util.List;

public record DadosVisualizacaoAllUsuarios(
        Long id,
        String codigo,
        String nome,
        String email,
        String telefone,
        List<String> gruposAssignados,
        Boolean administrador
) {
    public DadosVisualizacaoAllUsuarios(Usuario usuario) {
        this(usuario.getId(),
                usuario.getCodigo(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getNomeGruposAssignados(),
                usuario.isAdministrador());
    }
}
