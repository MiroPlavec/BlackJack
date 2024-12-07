package players;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected String name;
    protected List<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCardsValue(){
        int sum = 0;
        for(Card playerCard : cards){
            sum += playerCard.getRank();
        }
        return sum;
    }

    // save one card to player cards
    // if value of cards is more than 21 return true
    public boolean takeCard(Card card){
        cards.add(card);
        int sum = getCardsValue();
        return sum>21;
    }

    // remove all cards from list
    // every round player gets new cards
    public void clearHands(){
        while(!cards.isEmpty()){
            cards.remove(0);
        }
    }

    abstract public String showCards();

}
