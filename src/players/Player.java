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

    public void takeCard(Card card){
        cards.add(card);
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
