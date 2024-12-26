package projetotechsupport.apitechsupport.model.ticket;

import java.util.List;

public record CoursePageDTO(List<DadosVisualizacaoTicketByTipo> tickets, long totalTickets, int totalPages) {
}
