package utils;

import models.Card;
import models.Color;

import java.util.*;

public class CardsUtil {

    private CardsUtil() {}

    public static Stack<Card> createCardsDeck(int numCards) {
        List<Card> cards = new ArrayList<>();
        int numberOfColors = Color.values().length;
        int cardsPerColor = numCards / numberOfColors;
        for (Color color: Color.values()) {
            for (int i=0; i<cardsPerColor; i++) {
                cards.add(new Card(color));
            }
        }
        Random random = new Random();
        while (numCards - cards.size() > 0) {
            Color color = Color.values()[random.nextInt(numberOfColors)];
            cards.add(new Card(color));
        }
        Collections.shuffle(cards);
        Stack<Card> cardsStack = new Stack<>();
        cardsStack.addAll(cards);
        return cardsStack;
    }
}
