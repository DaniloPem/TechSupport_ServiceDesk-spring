package projetotechsupport.apitechsupport.model.grupoAssignado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DadosCadastroGrupoAssignado(
        @NotBlank
        String nome,
        @NotEmpty
        List<Long> categoriasId
) {
}
