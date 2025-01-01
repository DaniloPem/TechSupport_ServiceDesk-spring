package projetotechsupport.apitechsupport.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;
import projetotechsupport.apitechsupport.service.GrupoAssignadoService;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;

@RestController
@RequestMapping("/api/gruposAssignados")
@AllArgsConstructor
public class GrupoAssignadoController {

    private final GrupoAssignadoService grupoAssignadoService;

    @GetMapping
    public @ResponseBody GrupoAssignadoPageDTO listarAllGruposAssignados(@RequestParam String filter,
                                                                         @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                                         @RequestParam(defaultValue = "30") @Positive @Max(30) int pageSize) {
        return grupoAssignadoService.findAllGruposAssignados(filter, page, pageSize);
    }

    @GetMapping("/por-categoria/{categoriaId}")
    public @ResponseBody List<IdNomeDTO> listar(@PathVariable Long categoriaId) {
        return grupoAssignadoService.findByCategoriaId(categoriaId);
    }
}
