package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRecord;
import projetotechsupport.apitechsupport.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/por-codigo")
    public @ResponseBody List<UsuarioRecord> listar(@RequestParam(value = "codigo") String codigo){
        return usuarioService.findByCodigoLike(codigo);
    }

}
