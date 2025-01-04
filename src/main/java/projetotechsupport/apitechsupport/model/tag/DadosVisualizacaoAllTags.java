package projetotechsupport.apitechsupport.model.tag;

import java.util.List;

public record DadosVisualizacaoAllTags(
        Long id,
        String name,
        String categoriaName,
        List<String> subtagsName
) {
    public DadosVisualizacaoAllTags(Tag tag) {
        this(tag.getId(),
                tag.getNome(),
                tag.getCategoria().getNome(),
                tag.getNomeSubTags());
    }
}
