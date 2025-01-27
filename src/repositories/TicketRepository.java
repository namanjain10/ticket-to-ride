package repositories;

import models.Ticket;

import java.util.List;

public interface TicketRepository {

    void saveBulkTickets(String gameId, List<Ticket> tickets);
    List<Ticket> getTickets(String gameId, Ticket.State state);
    void assignTicketToPlayer(String gameId, String ticketId, String playerId);
}
