package repositories;

import models.Card;

public interface CardsRepository {
    String save(Card card);
    Card get(String id);
    void update(String id, Card card);
}
