package projetotechsupport.apitechsupport.model.grupoAssignado;

import java.util.List;

public record GrupoAssignadoPageDTO(List<DadosVisualizacaoAllGruposAssignados> grupos, Long totalGrupos, int totalPages) {
}
