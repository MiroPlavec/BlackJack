import cards.Card;
import cards.Suit;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Card card = Card.getCard(Suit.CLUBS, 3);
        List<Card> deck = Card.getDeck();
        Card.printDeck(deck, 500);

    }
}
