package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;
    public @ResponseBody List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
}
