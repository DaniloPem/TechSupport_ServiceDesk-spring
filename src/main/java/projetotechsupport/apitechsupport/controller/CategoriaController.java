package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRecord;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;
import projetotechsupport.apitechsupport.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/por-nome")
    public @ResponseBody List<CategoriaRecord> listar(@RequestParam(value = "nome") String namePattern) {
        return categoriaService.findByNomeLike(namePattern);
    }
}
