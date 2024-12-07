import cards.Card;
import helpers.RenderScreen;
import players.Dealer;
import players.HumanPlayer;
import players.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BlackJack {

    // maximum players behind one table, dealer is not counting
    public static final int MAX_PLAYERS = 5;
    public static Random rnd = new Random();

    private int deckCount;
    private Dealer dealer;
    private List<Player> players;
    private LinkedList<Card> cards;

    public BlackJack(int deckCount, String dealerName, List<Player> players) {
        this.deckCount = deckCount;
        this.dealer = new Dealer(dealerName);
        this.players = players;
        players.add(0, dealer);
    }

    public BlackJack(String dealerName) {
        this(6, dealerName, new ArrayList<>());
    }

    public BlackJack(String dealerName, List<Player> players){
        this(6, dealerName, players);
    }

    public BlackJack(int deckCount, String dealerName){
        this(deckCount, dealerName, new ArrayList<>());
    }

    public boolean addPlayer(Player player){
        if(players.size() == MAX_PLAYERS) return false;

        players.add(1, player);
        return true;
    }

    public void removePlayer(Player player){
        if(players.indexOf(player) == -1) return;
        players.remove(player);
    }

    public void startGame(){

        cards = Card.getDeck(deckCount);
        makeCut();

        makeBet();

        dealCards();
        RenderScreen.showPlayerWindows(players);

        hitOrStand();

        showDealerSecondCard();

        RenderScreen.showPlayerWindows(players);

        dealer.makeMove(cards, players);



    }

    private void showDealerSecondCard(){
        dealer.setShowLastCard(true);
        RenderScreen.showPlayerWindows(players);
    }

    private void makeCut(){
        // cut represents how many cards from the end od our deck are not be used in game
        // it makes it harder for player who counts card operate effectively
        int cutStart = cards.size()/5;
        int cut = rnd.nextInt(cutStart, cutStart+10);
        while (cut != 0){
            cards.removeLast();
            cut--;
        }
    }

    public void makeBet(){
        for(Player player : players){
            if(player instanceof HumanPlayer humanPlayer){
                humanPlayer.makeBet();
            }

        }
    }

    private void dealCards(){
        for(int i=0; i<2; i++) {
            for (Player player : players) {
                player.takeCard(cards.pop());
            }
        }
    }

    private void hitOrStand(){
        for(Player player : players){
            if(player instanceof HumanPlayer humanPlayer){
                boolean isGameOn = humanPlayer.makeChoice();
                while (isGameOn){
                    boolean endPlayerMove = takeAnotherCard(humanPlayer);
                    if(endPlayerMove) break;
                    isGameOn = humanPlayer.makeChoice();
                }

            }
        }
    }

    private boolean takeAnotherCard(Player player){
        boolean endPlayerMove = player.takeCard(cards.pop());
        RenderScreen.showPlayerWindows(players);

        return endPlayerMove;
    }

}
