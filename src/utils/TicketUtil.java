package utils;

import models.Ticket;
import models.board.City;

import java.util.ArrayList;
import java.util.List;

public class TicketUtil {

    private TicketUtil() {}

    public static List<Ticket> getTicketsDeck(String gameId) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(gameId, City.PARIS, City.LISBON, 10, Ticket.State.IN_DECK, null));
        tickets.add(new Ticket(gameId, City.MOSCOW, City.AMSTERDAM, 15, Ticket.State.IN_DECK, null));
        tickets.add(new Ticket(gameId, City.WIEN, City.EDINBURGH, 20, Ticket.State.IN_DECK, null));
        tickets.add(new Ticket(gameId, City.ZURICH, City.WERSAW, 10, Ticket.State.IN_DECK, null));
        return tickets;
    }
}
