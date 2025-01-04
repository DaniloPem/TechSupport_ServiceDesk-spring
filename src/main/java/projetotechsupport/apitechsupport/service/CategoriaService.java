package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import projetotechsupport.apitechsupport.model.categoria.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.DadosVisualizacaoAllGruposAssignados;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public List<CategoriaRecord> findByNomeLike(String namePattern) {
        List<Categoria> categorias = categoriaRepository.findByNomeLike(namePattern + "%");
        return categorias.stream()
                .map(CategoriaRecord::new)
                .toList();
    }

    public CategoriaPageDTO findAllCategorias(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<Categoria> pageCategorias = categoriaRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllCategorias> categorias = pageCategorias.map(DadosVisualizacaoAllCategorias::new).toList();
        return new CategoriaPageDTO(categorias, pageCategorias.getTotalElements(), pageCategorias.getTotalPages());
    }
}
