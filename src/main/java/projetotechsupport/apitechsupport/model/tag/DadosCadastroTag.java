package projetotechsupport.apitechsupport.model.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroTag(
        @NotBlank
        String nome,
        @NotNull
        Long categoriaId,
        List<Long> subTagsId
) {
}