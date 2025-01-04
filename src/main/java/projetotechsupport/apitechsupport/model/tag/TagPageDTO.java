package projetotechsupport.apitechsupport.model.tag;

import java.util.List;

public record TagPageDTO(List<DadosVisualizacaoAllTags> tags, Long totalTags, int totalPages) {
}
