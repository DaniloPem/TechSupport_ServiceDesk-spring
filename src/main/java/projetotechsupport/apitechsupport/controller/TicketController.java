package projetotechsupport.apitechsupport.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TicketRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;

    public @ResponseBody List<Ticket> listar() {
        return ticketRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
