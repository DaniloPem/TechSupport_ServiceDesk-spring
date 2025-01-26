package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaPageDTO;
import projetotechsupport.apitechsupport.model.categoria.DadosVisualizacaoAllCategorias;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;
import projetotechsupport.apitechsupport.model.tag.*;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final SubtagRepository subtagRepository;

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

    public Tag criar(DadosCadastroTag dadosCadastroTag) {
        List<Subtag> subtags = getSubtags(dadosCadastroTag.subTagsId());
        Tag tag = new Tag(dadosCadastroTag, subtags);
        return tagRepository.save(tag);
    }

    public Tag atualizar(Long id, DadosCadastroTag dadosCadastroTag) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        Tag tag = tagOptional.orElseThrow(() -> new DataIntegrityViolationException("TAG NÃO EXISTE."));
        tag.setNome(dadosCadastroTag.nome());
        List<Subtag> subtags = getSubtags(dadosCadastroTag.subTagsId());
        tag.setSubtags(subtags);
        tagRepository.save(tag);
        return tag;
    }

    private List<Subtag> getSubtags(List<Long> subtagsId) {
        return subtagRepository.findAllById(subtagsId);
    }

    public void desabilitar(Long id) {
        Tag tag = tagRepository.getReferenceById(id);
        tag.desabilitar();
    }
}
