package projetotechsupport.apitechsupport.model.categoria;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosCadastroCategoria(
        @NotBlank
        String nome,
        @NotBlank
        List<Long> gruposAssignadosId,
        List<Long> tagsId
) {
}
