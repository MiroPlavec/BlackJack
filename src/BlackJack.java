import cards.Card;
import players.Dealer;
import players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackJack {

    // maximum players behind one table, dealer is not counting
    public static final int MAX_PLAYERS = 5;
    public static Random rnd = new Random();

    private int deckCount;
    private Dealer dealer;
    private List<Player> players;
    private List<Card> cards;

    public BlackJack(int deckCount, String dealerName, List<Player> players) {
        this.deckCount = deckCount;
        this.dealer = new Dealer(dealerName);
        this.players = players;
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

        players.add(player);
        return true;
    }

    public void removePlayer(Player player){
        if(players.indexOf(player) == -1) return;
        players.remove(player);
    }

    public void startGame(){
        cards = Card.getDeck(deckCount);
        int cutStart = cards.size()/5;
        int cut = rnd.nextInt(cutStart, cutStart+10);
        System.out.print(cut);
    }
}
