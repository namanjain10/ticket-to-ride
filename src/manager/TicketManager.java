package manager;

import models.Ticket;

import java.util.List;

public interface TicketManager {

    void initTickets(String gameId);
    void addTicketToPlayerFromDeck(String gameId, String playerId);
    List<Ticket> playerTickets(String gameId, String playerId);
}
