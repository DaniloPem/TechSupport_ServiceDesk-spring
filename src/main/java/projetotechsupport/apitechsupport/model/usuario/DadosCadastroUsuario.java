package projetotechsupport.apitechsupport.model.usuario;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        String telefone,
        List<Long> gruposAssignadosId,
        boolean administrador
) {
}
