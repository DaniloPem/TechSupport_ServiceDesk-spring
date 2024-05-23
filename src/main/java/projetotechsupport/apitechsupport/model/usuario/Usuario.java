package projetotechsupport.apitechsupport.model.usuario;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codigo;

    private String nome;

    private String email;

    private String telefone;

    @ManyToMany
    private GrupoAsignado grupo;

}
