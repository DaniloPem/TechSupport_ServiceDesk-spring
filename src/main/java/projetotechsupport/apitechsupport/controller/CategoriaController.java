package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.categoria.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.DadosCadastroGrupoAssignado;
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
    public @ResponseBody List<CategoriaRecord> listarPorNome(@RequestParam(value = "nome") String namePattern) {
        return categoriaService.findByNomeLike(namePattern);
    }

    @GetMapping("/{ids}")
    public @ResponseBody List<CategoriaRecord> listarPorIds(@PathVariable List<Long> ids) {
        return categoriaService.findByIds(ids);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> criar(@RequestBody @Valid DadosCadastroCategoria dadosCadastroCategoria) {
        return ResponseEntity.ok(categoriaService.criar(dadosCadastroCategoria).getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroCategoria dadosCadastroCategoria){
        categoriaService.atualizar(dadosCadastroCategoria, id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desabilitar(@PathVariable Long id) {
        categoriaService.desabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
