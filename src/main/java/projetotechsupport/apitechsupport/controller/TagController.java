package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
@AllArgsConstructor
public class TagController {

    private final TagRepository tagRepository;

    @GetMapping("/por-categoria/{categoriaId}")
    public @ResponseBody List<Tag> listar(@PathVariable Long categoriaId) {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().filter(tag -> tag.getCategoria().getId().equals(categoriaId)).collect(Collectors.toList());}
}
