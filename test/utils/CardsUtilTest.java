package utils;

import models.cards.Card;

import java.util.Stack;

public class CardsUtilTest {

    public static void main(String[] args) {
        Stack<Card> cards = CardsUtil.createCardsDeck("abc", 10);
    }
}
