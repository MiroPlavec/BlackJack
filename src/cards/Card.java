package cards;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private int rank;
    private Suit suit;
    private String face;

    private Card(int rank, Suit suit, String face) {
        this.rank = rank;
        this.suit = suit;
        this.face = face;
    }

    /**
     * Create specific card based on input parameters.
     * Parameter <code>rank</code> is value from range 2-14.
     * It is because cards for black jack goes 2,3,4,...,J,Q,K,A
     * @param suit
     * @param rank
     * @return A new instance of class Card
     */
    public static Card getCard(Suit suit, int rank){
        String[] highCardsFace = {"J", "Q", "K", "A"};
        String face = String.valueOf(rank);
        if(rank > 10){
            face = highCardsFace[rank - 11];
            rank = 10;
        }
        return new Card(rank, suit, face);
    }


    /**
     * For every value in Enum <code>Suit</code> create 13 cards.
     * @return List
     */
    public static List<Card> getDeck(){
        List<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(int i=2; i<15; i++){
                deck.add(getCard(suit, i));
            }
        }
        return deck;
    }

    /**
     * Print elements of list on specific count of rows.
     * If number of elements in one row is not whole number,
     * the rest of the elements are printed in last row.
     * @param deck - list to be shown
     * @param rowCount - on how many rows will list show
     */
    public static void printDeck(List<Card> deck, int rowCount){
        if(rowCount > deck.size()) rowCount = deck.size();
        if(rowCount < 1) rowCount = 1;

        int cardsInOneRow = deck.size() / rowCount;
        int rest = 0;

        for(int i=0; i<rowCount; i++){
            int start = cardsInOneRow * i;
            int end = (cardsInOneRow*i) + cardsInOneRow;
            if (i == rowCount-1) rest = deck.size() - (cardsInOneRow*rowCount);
            for (int j=start; j<end + rest; j++){
                System.out.print(deck.get(j));
            }
            System.out.println();
        }
    }
    public static void printDeck(List<Card> deck){
        printDeck(deck, 4);
    }

    @Override
    public String toString() {
        return "%s%c ".formatted(face, suit.getSuit());
    }
}