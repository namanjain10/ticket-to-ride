package utils;

import models.Card;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class CardsUtilTest {

    public static void main(String[] args) {
        Stack<Card> cards = CardsUtil.createCardsDeck(10);
    }
}
