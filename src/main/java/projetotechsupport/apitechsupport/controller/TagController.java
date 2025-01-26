package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.tag.DadosCadastroTag;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagPageDTO;
import projetotechsupport.apitechsupport.model.tag.TagRepository;
import projetotechsupport.apitechsupport.service.TagService;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public @ResponseBody TagPageDTO listarAllTags(@RequestParam String filter,
                                                  @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                  @RequestParam(defaultValue = "30") @Positive @Max(30) int pageSize){
        return tagService.findAllTags(filter, page, pageSize);
    }

    @GetMapping("/por-categoria/{categoriaId}")
    public @ResponseBody List<IdNomeDTO> listar(@PathVariable Long categoriaId) {
        return tagService.
                findByCategoriaId(categoriaId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> criar(@RequestBody @Valid DadosCadastroTag dadosCadastroTag) {
        return ResponseEntity.ok(tagService.criar(dadosCadastroTag).getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroTag dadosCadastroTag) {
        tagService.atualizar(id, dadosCadastroTag);
        return ResponseEntity.ok(id);
    }

}
