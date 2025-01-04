package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.grupoAssignado.DadosVisualizacaoAllGruposAssignados;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;
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

    public GrupoAssignadoPageDTO findAllGruposAssignados(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<GrupoAssignado> pageGrupoAssignados = grupoAssignadoRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllGruposAssignados> gruposAssignados = pageGrupoAssignados.map(DadosVisualizacaoAllGruposAssignados::new).toList();
        return new GrupoAssignadoPageDTO(gruposAssignados, pageGrupoAssignados.getTotalElements(), pageGrupoAssignados.getTotalPages());
    }

}
