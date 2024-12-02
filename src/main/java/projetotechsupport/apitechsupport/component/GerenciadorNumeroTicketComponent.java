package projetotechsupport.apitechsupport.component;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TicketRepository;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;

import java.lang.reflect.Array;
import java.util.*;

@Component
@RequiredArgsConstructor
public class GerenciadorNumeroTicketComponent {

    private final TicketRepository ticketRepository;
    private Set<String> numerosIncidentEmAndamento = new HashSet<>();
    private Set<String> numerosRequestEmAndamento = new HashSet<>();

    public String getNumeroTicket(TipoTicket tipoTicket) {
        Optional<Ticket> ultimoTicketOptional = ticketRepository.findTopByTipoOrderByNumeroTicketSegundoTipoDesc(tipoTicket);
        Optional<String> numeroOptional = ultimoTicketOptional.map(Ticket::getNumeroTicketSegundoTipo);
        Integer numeroNoBancoDeDados = numeroOptional.map(num -> Integer.parseInt(num.substring(2))).orElse(1);
        Integer numeroNaoSalvoNoBancoDeDados = switch (tipoTicket) {
            case REQUEST ->
                    numerosRequestEmAndamento.stream().map(num -> Integer.parseInt(num.substring(2))).max(Integer::compareTo).orElse(1);
            case INCIDENT ->
                    numerosIncidentEmAndamento.stream().map(num -> Integer.parseInt(num.substring(2))).max(Integer::compareTo).orElse(1);
        };
        int proximoNumero = Math.max(numeroNoBancoDeDados, numeroNaoSalvoNoBancoDeDados) + 1;
        int comprimentoNumero = 7;
        String numeroDoTicket = String.format("%0" + comprimentoNumero + "d", proximoNumero);
        String numeroDoTicketSegundoOTipo = tipoTicket.equals(TipoTicket.REQUEST) ? "RR" + numeroDoTicket : "IR" + numeroDoTicket;
        switch (tipoTicket) {
            case REQUEST -> numerosRequestEmAndamento.add(numeroDoTicketSegundoOTipo);
            case INCIDENT -> numerosIncidentEmAndamento.add(numeroDoTicketSegundoOTipo);
        }

        return numeroDoTicketSegundoOTipo;
    }


}
