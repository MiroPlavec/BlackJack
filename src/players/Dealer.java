package players;

import cards.Card;

public class Dealer extends Player{

    private boolean showLastCard = false;

    public Dealer(String name) {
        super(name);
    }

    @Override
    public String showCards() {
        String result = "";
        if(!showLastCard){
            result = cards.get(0).toString() + " ?";
        }else {
            for(Card card : cards){
                result += card.toString();
            }
        }
        return result;
    }
}
