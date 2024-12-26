package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetotechsupport.apitechsupport.model.ticket.*;
import projetotechsupport.apitechsupport.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

//    @GetMapping
//    public @ResponseBody CoursePageDTO listarTicketsPorTipo(@RequestParam TipoTicket type) {
//        return ticketService.findAllByTypeTicket(type);
//    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Long> create(@RequestBody @Valid DadosCadastroTicket dadosTicket) {
        return ResponseEntity.ok(ticketService.create(dadosTicket).getId());
    }

    @GetMapping("/proximo-numero-ticket")
    public String getNumeroProximoTicket(@RequestParam TipoTicket tipoTicket) {
        return this.ticketService.getNumeroTicket(tipoTicket);
    }

    @GetMapping("/{id}")
    public @ResponseBody DadosVisualizacaoTicketById getTicketById (@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroTicket dadosTicket) {
        ticketService.atualizarTicket(dadosTicket, id);
        return ResponseEntity.ok(id);
    }
}
