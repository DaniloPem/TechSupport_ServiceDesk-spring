package projetotechsupport.apitechsupport.model.usuario;

import java.util.List;

public record UsuarioPageDTO(List<DadosVisualizacaoAllUsuarios> usuarios, long totalUsuarios, int totalPages) {
}
