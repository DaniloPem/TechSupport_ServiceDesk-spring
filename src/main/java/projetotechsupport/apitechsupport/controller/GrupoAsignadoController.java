package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRepository;
import projetotechsupport.apitechsupport.service.GrupoAssignadoService;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/gruposAssignados")
@AllArgsConstructor
public class GrupoAsignadoController {

    private final GrupoAssignadoService grupoAssignadoService;

    @GetMapping("/por-categoria/{categoriaId}")
    public @ResponseBody List<IdNomeDTO> listar(@PathVariable Long categoriaId) {
        return grupoAssignadoService.findByCategoriaId(categoriaId);
    }
}
