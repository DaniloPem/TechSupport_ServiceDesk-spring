package projetotechsupport.apitechsupport.model.ticket;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime dataEHorarioDeCriacao;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    private String numeroTicketSegundoTipo;

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
    @JoinColumn(name = "categoria_nome", nullable = false)
    private Categoria categoria;

    @Column(length = 1000)
    private String solucao;

    @Column(length = 1000)
    private String solucaoDadosPessoais;

    public Ticket(DadosCadastroTicket dadosTicket) {
        this.titulo = dadosTicket.titulo();
        Random random = new Random();
        this.numeroTicketSegundoTipo = "X" + random.nextInt(100);
        this.titulo = dadosTicket.titulo();
        this.reportadoPor = dadosTicket.reportadoPor();
        this.reportadoPara = dadosTicket.reportadoPara();
        this.grupoAsignado = dadosTicket.grupoAsignado();
        this.descricao = dadosTicket.descricao();
        this.dadosPessoais = dadosTicket.dadosPessoais();
        this.categoria = dadosTicket.categoria();
        this.solucao = dadosTicket.solucao();
        this.solucaoDadosPessoais = dadosTicket.solucaoDadosPessoais();
    }
}
