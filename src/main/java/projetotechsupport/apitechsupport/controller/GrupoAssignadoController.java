package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRecord;
import projetotechsupport.apitechsupport.model.grupoAssignado.DadosCadastroGrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRecord;
import projetotechsupport.apitechsupport.model.usuario.DadosCadastroUsuario;
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

    @GetMapping("/por-nome")
    public @ResponseBody List<GrupoAssignadoRecord> listar(@RequestParam(value = "nome") String namePattern) {
        return grupoAssignadoService.findByNomeLike(namePattern);
    }

    @GetMapping("/{ids}")
    public List<GrupoAssignadoRecord> getGruposByIds(@PathVariable List<Long> ids) {
        return grupoAssignadoService.findByIds(ids);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody @Valid DadosCadastroGrupoAssignado dadosCadastroGrupoAssignado) {
        return ResponseEntity.ok(grupoAssignadoService.criarGrupo(dadosCadastroGrupoAssignado).getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroGrupoAssignado dadosCadastroGrupoAssignado) {
        grupoAssignadoService.atualizarGrupo(dadosCadastroGrupoAssignado, id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitar(@PathVariable Long id) {
        grupoAssignadoService.deshabilitar(id);
        return ResponseEntity.noContent().build();
    }
}
