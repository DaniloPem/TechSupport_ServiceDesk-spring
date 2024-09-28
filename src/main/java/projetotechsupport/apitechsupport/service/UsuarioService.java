package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.usuario.Usuario;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRecord;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioRecord> findByCodigoLike(String codigo) {
        List<Usuario> usuarios = usuarioRepository.findByCodigoLike('%' + codigo + '%');
        return usuarios.stream().map(UsuarioRecord::new).toList();
    }
}
