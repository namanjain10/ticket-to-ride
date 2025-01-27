package repositories.impl;

import models.Ticket;
import repositories.TicketRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTicketRepository implements TicketRepository {

    private final Map<String, Map<Ticket.State, List<Ticket>>> gameVsStateVsTickets = new HashMap<>();

    @Override
    public void saveBulkTickets(String gameId, List<Ticket> tickets) {
        Map<Ticket.State, List<Ticket>> stateListMap = new HashMap<>();
        for (Ticket ticket: tickets) {
            Ticket.State state = ticket.getState();
            List<Ticket> stateTickets = stateListMap.get(state);
            if (stateTickets == null) {
                stateTickets = new ArrayList<>();
            }
            stateTickets.add(ticket);
            stateListMap.put(state, stateTickets);
        }
        gameVsStateVsTickets.put(gameId, stateListMap);
    }

    @Override
    public List<Ticket> getTickets(String gameId, Ticket.State state) {
        return gameVsStateVsTickets.get(gameId)
                .get(state);
    }

    @Override
    public void assignTicketToPlayer(String gameId, String ticketId, String playerId) {
        List<Ticket> deckTickets = getTickets(gameId, Ticket.State.IN_DECK);
        Ticket selectedTicket = deckTickets.stream()
                .filter(ticket -> ticket.getId().equals(ticketId))
                .findFirst()
                .orElse(null);
        if (selectedTicket == null) {
            return;
        }
        selectedTicket.addCardToPlayer(playerId);
        List<Ticket> playerTickets = getTickets(gameId, Ticket.State.WITH_PLAYER);
        if (playerTickets == null) {
            playerTickets = new ArrayList<>();
        }
        playerTickets.add(selectedTicket);
        gameVsStateVsTickets.get(gameId)
                        .put(Ticket.State.WITH_PLAYER, playerTickets);
        deckTickets.remove(selectedTicket);
    }
}
