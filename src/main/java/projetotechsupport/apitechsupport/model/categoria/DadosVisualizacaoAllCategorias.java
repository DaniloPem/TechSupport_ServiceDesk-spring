package projetotechsupport.apitechsupport.model.categoria;

import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;

import java.util.List;

public record DadosVisualizacaoAllCategorias(
        Long id,
        String nome,
        List<String> gruposAssignadosNome,
        List<String> tagsNome
) {
    public  DadosVisualizacaoAllCategorias(Categoria categoria) {
        this(categoria.getId(),
                categoria.getNome(),
                categoria.getNomeGruposAssignados(),
                categoria.getNomeTag());
    }
}
