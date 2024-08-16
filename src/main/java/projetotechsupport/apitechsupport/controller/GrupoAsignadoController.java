package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRepository;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/gruposAssignados")
@AllArgsConstructor
public class GrupoAsignadoController {

    private final GrupoAssignadoRepository grupoAssignadoRepository;

    @GetMapping("/por-categoria/{categoriaId}")
    public @ResponseBody List<GrupoAssignado> listar(@PathVariable Long categoriaId) {
        return Collections.emptyList();
    }
}
