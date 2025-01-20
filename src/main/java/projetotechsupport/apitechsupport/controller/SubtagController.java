package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;
import projetotechsupport.apitechsupport.service.SubtagService;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subtags")
@AllArgsConstructor
public class SubtagController {

    private final SubtagService subtagService;

    @GetMapping("/por-tag/{tagId}")
    public @ResponseBody List<IdNomeDTO> listar(@PathVariable Long tagId) {
        return subtagService.findByTagId(tagId);
    }
}
