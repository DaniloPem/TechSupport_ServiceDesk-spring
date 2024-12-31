package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.usuario.*;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioRecord> findByCodigoLike(String codigo) {
        if (codigo.isEmpty()) return Collections.emptyList();
        List<Usuario> usuarios = usuarioRepository.findByCodigoLike('%' + codigo + '%');
        return usuarios.stream().map(UsuarioRecord::new).toList();
    }

    public UsuarioPageDTO findAllUser(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<Usuario> pageUsuarios = usuarioRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllUsuarios> usuarios = pageUsuarios.map(DadosVisualizacaoAllUsuarios::new).toList();
        return new UsuarioPageDTO(usuarios, pageUsuarios.getTotalElements(), pageUsuarios.getTotalPages());
    }
}
