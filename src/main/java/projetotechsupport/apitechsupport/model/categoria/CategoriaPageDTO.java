package projetotechsupport.apitechsupport.model.categoria;

import projetotechsupport.apitechsupport.model.grupoAssignado.DadosVisualizacaoAllGruposAssignados;

import java.util.List;

public record CategoriaPageDTO(List<DadosVisualizacaoAllCategorias> categorias, Long totalCategorias, int totalPages) {
}
