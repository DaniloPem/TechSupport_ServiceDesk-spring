package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRecord;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public List<CategoriaRecord> findByNomeLike(String namePattern) {
        List<Categoria> categorias = categoriaRepository.findByNomeLike(namePattern + "%");
        return categorias.stream().map(categoria -> new CategoriaRecord(categoria.getNome())).toList();
    }
}
