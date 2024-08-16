package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.tag.Tag;
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

}
