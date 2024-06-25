package projetotechsupport.apitechsupport.model.grupoAsignado;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.util.List;

@Data
@Entity
public class GrupoAsignado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Categoria categoria;

    @ManyToMany(mappedBy = "gruposAsignados", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}
