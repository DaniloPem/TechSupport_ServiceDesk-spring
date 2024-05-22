package projetotechsupport.apitechsupport.model.ticket;

import jakarta.persistence.*;
import lombok.Data;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    private Long numeroTicketSegundoTipo;

    @Column(length = 150, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario reportadoPor;

    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario reportadoPara;

    @ManyToOne
    @JoinColumn(name = "grupo_nome")
    private GrupoAsignado grupoAsignado;

    @ManyToOne
    @JoinColumn(name = "usuario_codigo")
    private Usuario gestionadoPor;

    @Column(length = 1000, nullable = false)
    private String descricao;

    @Column(length = 1000, nullable = false)
    private String dadosPessoais;

    @ManyToOne
    @JoinColumn(name = "categoria_nome")
    private Usuario categoria;

    @Column(length = 1000)
    private String solucao;

    @Column(length = 1000)
    private String solucaoDadosPessoais;
}
