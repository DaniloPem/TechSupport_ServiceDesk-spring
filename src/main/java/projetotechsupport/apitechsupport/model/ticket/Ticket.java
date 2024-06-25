package projetotechsupport.apitechsupport.model.ticket;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime dataEHorarioDeCriacao;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    private String numeroTicketSegundoTipo;

    @Column(length = 150, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "reportado_por_id")
    private Usuario reportadoPor;

    @ManyToOne
    @JoinColumn(name = "reportado_para_id")
    private Usuario reportadoPara;

    @ManyToOne
    @JoinColumn(name = "grupo_asignado_id", nullable = false)
    private GrupoAsignado grupoAsignado;

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
        this.categoriaReportada = dadosTicket.categoriaReportada();
        this.categoriaAfetada = dadosTicket.categoriaAfetada();
        this.tag = dadosTicket.tag();
        this.subtag = dadosTicket.subtag();
        this.solucao = dadosTicket.solucao();
        this.solucaoDadosPessoais = dadosTicket.solucaoDadosPessoais();
    }
}
