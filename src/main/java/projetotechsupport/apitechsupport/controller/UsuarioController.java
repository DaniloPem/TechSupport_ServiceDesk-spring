package projetotechsupport.apitechsupport.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.usuario.UsuarioPageDTO;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRecord;
import projetotechsupport.apitechsupport.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public @ResponseBody UsuarioPageDTO listarAllUsuarios(@RequestParam String filter,
                                                          @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                          @RequestParam(defaultValue = "30") @Positive @Max(30) int pageSize) {
        return usuarioService.findAllUser(filter, page, pageSize);
    }

    @GetMapping("/por-codigo")
    public @ResponseBody List<UsuarioRecord> listar(@RequestParam(value = "codigo") String codigo){
        return usuarioService.findByCodigoLike(codigo);
    }

}
