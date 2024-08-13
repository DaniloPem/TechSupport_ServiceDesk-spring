package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subtags")
@AllArgsConstructor
public class SubtagController {

    private final SubtagRepository subtagRepository;

    @GetMapping("/por-tag/{tagId}")
    public @ResponseBody List<Subtag> listar(@PathVariable Long tagId) {
        List<Subtag> subTags = subtagRepository.findAll();
        return subTags.stream()
                .filter(subtag -> subtag.getTag().getId().equals(tagId))
                .collect(Collectors.toList());
    }
}
