package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.ticket.DadosCadastroTicket;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TicketRepository;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;
import projetotechsupport.apitechsupport.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    public @ResponseBody List<Ticket> listar() {
        return ticketService.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ticket create(@RequestBody DadosCadastroTicket dadosTicket) {
        return this.ticketService.create(dadosTicket);
    }

    @GetMapping("/proximo-numero-ticket")
    public String getNumeroProximoTicket(@RequestParam TipoTicket tipoTicket) {
        return this.ticketService.getNumeroTicket(tipoTicket);
    }

}
