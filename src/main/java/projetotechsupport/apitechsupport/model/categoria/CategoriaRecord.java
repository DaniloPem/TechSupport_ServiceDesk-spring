package projetotechsupport.apitechsupport.model.categoria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record CategoriaRecord(String nome) {
    public CategoriaRecord(Categoria categoria) {
        this(categoria.getNome());
    }
}
