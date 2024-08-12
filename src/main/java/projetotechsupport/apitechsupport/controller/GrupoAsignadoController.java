package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAssignadoRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class GrupoAsignadoController {

    private final GrupoAssignadoRepository grupoAssignadoRepository;

    public @ResponseBody List<GrupoAssignado> listar() {
        return grupoAssignadoRepository.findAll();
    }
}
