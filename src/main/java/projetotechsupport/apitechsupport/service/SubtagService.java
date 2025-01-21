package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.subtag.DadosCadastroSubtag;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubtagService {

    private final SubtagRepository subtagRepository;

    public List<IdNomeDTO> findByTagId(Long tagId) {
        List<Subtag> subTags = subtagRepository.findByTagId(tagId);
        return subTags.stream().map(subtag -> new IdNomeDTO(subtag.getId(), subtag.getNome())).toList();
    }

    public Subtag create(DadosCadastroSubtag dadosCadastroSubtag){
        Subtag subtag = new Subtag(dadosCadastroSubtag);
        return subtagRepository.save(subtag);
    }

}
