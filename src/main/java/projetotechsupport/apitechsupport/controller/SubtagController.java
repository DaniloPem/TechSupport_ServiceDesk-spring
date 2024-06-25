package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubtagController {

    private final SubtagRepository subtagRepository;
    public @ResponseBody List<Subtag> listar() {
        return subtagRepository.findAll();
    }
}
