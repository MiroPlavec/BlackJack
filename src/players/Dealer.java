package players;

import cards.Card;
import helpers.RenderScreen;

import java.util.LinkedList;
import java.util.List;

public class Dealer extends Player{

    private boolean showLastCard = false;

    public Dealer(String name) {
        super(name);
    }

    public void setShowLastCard(boolean showLastCard) {
        this.showLastCard = showLastCard;
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





    // take cards until you hit 21 or until you have the highest score
    public void makeMove(LinkedList<Card> cards, List<Player> players){
        boolean endMoves = false;

        //int[] playersHands = new int[players.size() - 1];
        int maxHand = 0;
        for(Player player : players){
            if(player instanceof Dealer) continue;
            int playerHand = player.getCardsValue();
            if(playerHand <= 21 && playerHand > maxHand) maxHand = playerHand;
        }

        int dealerHand = getCardsValue();
        while(dealerHand < maxHand || dealerHand <= 21){
            takeCard(cards.pop());
            RenderScreen.showPlayerWindows(players);
            dealerHand = getCardsValue();
        }

    }
}
