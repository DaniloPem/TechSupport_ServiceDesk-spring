package projetotechsupport.apitechsupport.model.ticket;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, updatable = false, name = "data_e_horario_de_criacao")  //mudado
    @CreationTimestamp
    private LocalDateTime dataEHorarioDeCriacao;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    @Column(length = 9, nullable = false)
    private String numeroTicketSegundoTipo;

    @Column(length = 150, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "reportado_por_id", nullable = false)
    private Usuario reportadoPor;

    @ManyToOne
    @JoinColumn(name = "reportado_para_id")
    private Usuario reportadoPara;

    @ManyToOne
    @JoinColumn(name = "grupo_assignado_id", nullable = false)
    private GrupoAssignado grupoAssignado;

    @ManyToOne
    @JoinColumn(name = "gerenciado_por_id")
    private Usuario gerenciadoPor;

    @Column(length = 1000, nullable = false)
    private String descricao;

    @Column(length = 1000, nullable = false)
    private String dadosPessoais;

    @ManyToOne
    @JoinColumn(name = "categoria_reportada_id", nullable = false)
    private Categoria categoriaReportada;

    @ManyToOne
    @JoinColumn(name = "categoria_afetada_id", nullable = false)
    private Categoria categoriaAfetada;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "subtag_id")
    private Subtag subtag;

    @Column(length = 1000)
    private String solucao;

    @Column(length = 1000)
    private String solucaoDadosPessoais;

    public Ticket(DadosCadastroTicket dadosTicket, Usuario reportadoPor, Usuario reportadoPara, GrupoAssignado grupoAssignado,
                  Categoria categoriaReportada, Categoria categoriaAfetada, Tag tag, Subtag subtag) {
        this.tipo = dadosTicket.tipo();
        this.status = Status.WORKING;
        this.titulo = dadosTicket.titulo();
        this.numeroTicketSegundoTipo = dadosTicket.numeroTicketSegundoTipo();
        this.reportadoPor = reportadoPor;
        this.reportadoPara = reportadoPara;
        this.grupoAssignado = grupoAssignado;
        this.descricao = dadosTicket.descricao();
        this.dadosPessoais = dadosTicket.dadosPessoais();
        this.categoriaReportada = categoriaReportada;
        this.categoriaAfetada = categoriaAfetada;
        this.tag = tag;
        this.subtag = subtag;
        this.solucao = dadosTicket.solucao();
        this.solucaoDadosPessoais = dadosTicket.solucaoDadosPessoais();
    }

}
