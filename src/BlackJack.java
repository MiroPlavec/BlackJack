import cards.Card;
import helpers.RenderScreen;
import players.Dealer;
import players.HumanPlayer;
import players.Player;

import java.util.*;

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



    public void startGame(){

        cards = Card.getDeck(deckCount);
        Card.shuffleDeck(cards);
        makeCut();


        while (players.size() > 1) {
            makeBet();

            dealCards();
            RenderScreen.showPlayerWindows(players);

            hitOrStand();

            showDealerSecondCard();

            RenderScreen.showPlayerWindows(players);

            dealer.makeMove(cards, players);

            showWinners();

            clearPlayersHand();

            askToContinue();
        }


    }

    private void askToContinue(){
        Scanner scanner = new Scanner(System.in);
        // iterator
        Iterator<Player> playerIterator = players.iterator();
        while(playerIterator.hasNext()){
            Player player = playerIterator.next();
            if (player instanceof Dealer) continue;
            System.out.println("%s do you wanna continue in game ? (yes/no)".formatted(player.getName()));
            String choice = scanner.nextLine();
            if(!choice.equals("yes")){
                System.out.println("Thank you for play " + player.getName() + ", have a nice day.");
                playerIterator.remove();
            }
        }
    }

    private void clearPlayersHand(){
        for (Player player : players) {
            player.clearHands();
            if(player instanceof Dealer) ((Dealer)player).setShowLastCard(false);
        }
    }

    private void showWinners(){
        int dealerHand = (dealer.getCardsValue() <=21) ? dealer.getCardsValue() : 0;
        boolean noWinner = true;
        for(Player player : players){
            if(player instanceof Dealer) continue;
            int playerHand = player.getCardsValue();
            if(playerHand > dealerHand && playerHand <= 21){
                System.out.println(player.getName() + " you won.");
                ((HumanPlayer)player).addMoneyWon(true);
                noWinner = false;
            }else{
                ((HumanPlayer)player).addMoneyWon(false);
            }
        }
        if(noWinner) System.out.println("Dealer won this round.");
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
                humanPlayer.makeMove(cards, players);
            }
        }
    }

}
