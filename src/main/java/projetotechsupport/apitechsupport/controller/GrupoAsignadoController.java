package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignadoRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class GrupoAsignadoController {

    private final GrupoAsignadoRepository grupoAsignadoRepository;

    public @ResponseBody List<GrupoAsignado> listar() {
        return grupoAsignadoRepository.findAll();
    }
}
