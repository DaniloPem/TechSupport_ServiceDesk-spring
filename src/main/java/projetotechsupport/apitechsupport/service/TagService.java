package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaPageDTO;
import projetotechsupport.apitechsupport.model.categoria.DadosVisualizacaoAllCategorias;
import projetotechsupport.apitechsupport.model.tag.DadosVisualizacaoAllTags;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagPageDTO;
import projetotechsupport.apitechsupport.model.tag.TagRepository;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<IdNomeDTO> findByCategoriaId(Long categoriaId) {
        List<Tag> tags = tagRepository.findByCategoriaId(categoriaId);
        return tags.stream()
                .map(tag -> new IdNomeDTO(tag.getId(), tag.getNome()))
                .toList();
    }

    public TagPageDTO findAllTags(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<Tag> pageTag = tagRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllTags> tags = pageTag.map(DadosVisualizacaoAllTags::new).toList();
        return new TagPageDTO(tags, pageTag.getTotalElements(), pageTag.getTotalPages());
    }
}
