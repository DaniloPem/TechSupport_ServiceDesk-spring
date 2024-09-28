package projetotechsupport.apitechsupport.model.categoria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record CategoriaRecord(Long id, String nome) {
    public CategoriaRecord(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
