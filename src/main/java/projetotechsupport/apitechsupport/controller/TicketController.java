package projetotechsupport.apitechsupport.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @GetMapping
    public @ResponseBody CoursePageDTO listarTicketsPorTipo(@RequestParam TipoTicket type,
                                                            @RequestParam String filter,
                                                            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                            @RequestParam(defaultValue = "30") @Positive @Max(30) int pageSize) {
        return ticketService.findAllByTypeTicket(type, filter, page, pageSize);
    }

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
