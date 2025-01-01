package projetotechsupport.apitechsupport.model.ticket;

import java.util.List;

public record TicketPageDTO(List<DadosVisualizacaoTicketByTipo> tickets, long totalTickets, int totalPages) {
}
