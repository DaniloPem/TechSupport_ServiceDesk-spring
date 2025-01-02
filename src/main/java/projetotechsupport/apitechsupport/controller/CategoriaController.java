package projetotechsupport.apitechsupport.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaPageDTO;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRecord;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;
import projetotechsupport.apitechsupport.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public @ResponseBody CategoriaPageDTO listarAllGruposAssignados(@RequestParam String filter,
                                                                    @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                                    @RequestParam(defaultValue = "30") @Positive @Max(30) int pageSize) {
        return categoriaService.findAllCategorias(filter, page, pageSize);
    }
    @GetMapping("/por-nome")
    public @ResponseBody List<CategoriaRecord> listar(@RequestParam(value = "nome") String namePattern) {
        return categoriaService.findByNomeLike(namePattern);
    }
}
