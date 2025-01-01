package projetotechsupport.apitechsupport.model.grupoAssignado;

import java.util.List;

public record DadosVisualizacaoAllGruposAssignados(
        Long id,
        String nome,
        List<String> categoriasNome,
        List<String> usuariosNome
) {
    public  DadosVisualizacaoAllGruposAssignados(GrupoAssignado grupoAssignado) {
        this(grupoAssignado.getId(),
                grupoAssignado.getNome(),
                grupoAssignado.getNomeCategorias(),
                grupoAssignado.getNomeUsuarios());
    }
}
