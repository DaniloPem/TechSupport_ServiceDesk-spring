package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagRepository;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class TagController {

    private final TagRepository tagRepository;

    public @ResponseBody List<Tag> listar() {return tagRepository.findAll();}
}
