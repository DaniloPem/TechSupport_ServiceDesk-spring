package projetotechsupport.apitechsupport.model.grupoAssignado;

import java.util.List;

public record DadosVisualizacaoGrupoAssignado(
        Long id,
        String nome,
        List<Long> categoriasId,
        List<String> categoriasNome) {

    public DadosVisualizacaoGrupoAssignado(GrupoAssignado grupoAssignado){
        this(grupoAssignado.getId(),
        grupoAssignado.getNome(),
        grupoAssignado.getIdCategorias(),
        grupoAssignado.getNomeCategorias());
    }

}
