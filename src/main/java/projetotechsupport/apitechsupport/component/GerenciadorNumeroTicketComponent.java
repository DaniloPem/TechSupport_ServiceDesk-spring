package projetotechsupport.apitechsupport.component;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TicketRepository;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GerenciadorNumeroTicketComponent {

    private final TicketRepository ticketRepository;
    private Set<String> numerosIncidentEmAndamento = new HashSet<>();
    private Set<String> numerosRequestEmAndamento = new HashSet<>();

    public String getNumeroTicket(TipoTicket tipoTicket) {
        Optional<Ticket> ultimoTicketOptional = ticketRepository.findTopByTipoTicketOrderBynumeroTicketSegundoTipoDesc(tipoTicket);
        Optional<String> numeroOptional = ultimoTicketOptional.map(Ticket::getNumeroTicketSegundoTipo);
        Integer numero = numeroOptional.map(num -> Integer.parseInt(num.substring(2)) + 1).orElse(1);
        int comprimentoNumero = 7;
        String numeroDoTicket = String.format("%0" + comprimentoNumero + "d", numero);
        String numeroDoTicketSegundoOTipo = tipoTicket.equals(TipoTicket.REQUEST) ? "RR" + numeroDoTicket : "IR" + numeroDoTicket;
        switch (tipoTicket) {
            case REQUEST -> numerosRequestEmAndamento.add(numeroDoTicketSegundoOTipo);
            case INCIDENT -> numerosIncidentEmAndamento.add(numeroDoTicketSegundoOTipo);
        }
        return numeroDoTicketSegundoOTipo;
    }


}
