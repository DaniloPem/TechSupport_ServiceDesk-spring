package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRepository;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GrupoAssignadoService {

    private final GrupoAssignadoRepository grupoAssignadoRepository;

    public List<IdNomeDTO> findByCategoriaId(Long categoriaId) {
        List<GrupoAssignado> gruposAssignados = grupoAssignadoRepository.findByCategoriasId(categoriaId);
        return gruposAssignados.stream().map(grupoAssignado -> new IdNomeDTO(grupoAssignado.getId(), grupoAssignado.getNome())).toList();
    }
}
