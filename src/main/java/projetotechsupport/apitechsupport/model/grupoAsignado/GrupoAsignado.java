package projetotechsupport.apitechsupport.model.grupoAsignado;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import projetotechsupport.apitechsupport.model.categoria.Categoria;

@Data
@Entity
public class GrupoAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Categoria categoria;
}
