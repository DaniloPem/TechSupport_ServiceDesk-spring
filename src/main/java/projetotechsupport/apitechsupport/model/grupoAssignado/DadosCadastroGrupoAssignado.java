package projetotechsupport.apitechsupport.model.grupoAssignado;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DadosCadastroGrupoAssignado(
        @NotBlank
        String nome,
        @NotBlank
        List<Long> categoriasId,
        @NotBlank
        List<Long> usuariosId
) {
}
