package projetotechsupport.apitechsupport.model.grupoAssignado;

import java.util.List;

public record GrupoAssignadoPageDTO(List<DadosVisualizacaoAllGruposAssignados> dadosVisualizacaoAllGruposAssignados, Long totalGruposAssignados, int totalPages) {
}
