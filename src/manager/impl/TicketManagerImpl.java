package manager.impl;

import exception.NoTicketLeftInDeck;
import manager.TicketManager;
import models.Ticket;
import repositories.TicketRepository;
import utils.TicketUtil;

import java.util.Collections;
import java.util.List;

public class TicketManagerImpl implements TicketManager {

    private final TicketRepository ticketRepository;
    private static final int NUM_TICKETS_PER_PLAYER = 1;

    public TicketManagerImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void initTickets(String gameId) {
        ticketRepository.saveBulkTickets(gameId, TicketUtil.getTicketsDeck(gameId));
    }

    @Override
    public void addTicketToPlayerFromDeck(String gameId, String playerId) {
        for (int i=0; i<NUM_TICKETS_PER_PLAYER; i++) {
            Ticket ticketFromDeck = ticketRepository.getTickets(gameId, Ticket.State.IN_DECK).stream()
                    .findAny()
                    .orElse(null);
            if (ticketFromDeck == null) {
                throw new NoTicketLeftInDeck("No tickets are found in deck");
            }
            ticketRepository.assignTicketToPlayer(gameId, ticketFromDeck.getId(), playerId);
        }
    }

    @Override
    public List<Ticket> playerTickets(String gameId, String playerId) {
        List<Ticket> playerTickets = ticketRepository.getTickets(gameId, Ticket.State.WITH_PLAYER);
        if (playerTickets == null || playerTickets.isEmpty()) {
            return Collections.emptyList();
        }
        return playerTickets.stream()
                .filter(ticket -> playerId.equals(ticket.getPlayerId()))
                .toList();
    }
}
