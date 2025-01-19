package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRepository;
import projetotechsupport.apitechsupport.model.ticket.DadosVisualizacaoTicketById;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.usuario.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final GrupoAssignadoRepository grupoAssignadoRepository;

    public DadosVisualizacaoUsuario getUsuarioById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO NÃO EXISTE"));
        return new DadosVisualizacaoUsuario(usuario);
    }
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

    public Usuario create(DadosCadastroUsuario dadosCadastroUsuario) {
        List<GrupoAssignado> gruposAssignados = getGruposAssignados(dadosCadastroUsuario.gruposAssignadosId());
        Usuario usuario = new Usuario(dadosCadastroUsuario, gruposAssignados, getCodigoDoUsuario());
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(DadosCadastroUsuario dadosUsuario, Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Usuario usuario = usuarioOptional.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO NÃO EXISTE."));
        usuario.setNome(dadosUsuario.nome());
        usuario.setEmail(dadosUsuario.email());
        usuario.setTelefone(dadosUsuario.telefone());
        usuario.setAdministrador(dadosUsuario.administrador());
        List<GrupoAssignado> grupoAssignados = getGruposAssignados(dadosUsuario.gruposAssignadosId());
        usuario.setGruposAssignados(grupoAssignados);
        usuarioRepository.save(usuario);
        return usuario;
    }

    private List<GrupoAssignado> getGruposAssignados(List<Long> gruposAssignadosId) {
        return gruposAssignadosId != null ?
                grupoAssignadoRepository.findAllById(gruposAssignadosId) :
                Collections.emptyList();
    }

    public String getCodigoDoUsuario() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        Random random = new Random();
        StringBuilder codigoGerado = new StringBuilder();

        do {
            for (int i = 0; i < 7; i++) {
                if (i % 2 == 0) {
                    codigoGerado.append(letras.charAt(random.nextInt(letras.length() - 1)));
                } else {
                    String conjunto = random.nextBoolean() ? letras : numeros;
                    codigoGerado.append(conjunto.charAt(random.nextInt(conjunto.length() - 1)));
                }
            }
        } while (usuarioRepository.findByCodigo(codigoGerado.toString()).isPresent());

        return codigoGerado.toString();
    }
}
