package players;

import cards.Card;
import helpers.RenderScreen;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player{

    private int money;
    private int bet;

    public HumanPlayer(String name, int money) {
        super(name);
        this.money = money;
        bet = 0;
    }

    public void addMoneyWon(Boolean win){
        if(win) money += bet;
        else money -= bet;

        bet = 0;
    }

    public void makeBet(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(name + " please place your bet. Your current balance is " + money + " €.");
            bet = Integer.parseInt(scanner.nextLine());
        }while (!checkBet());

    }

    private boolean checkBet(){
        if(bet <= money && bet>0) return true;
        System.out.println("Your bet must be in range 1 to " + money + " €.");
        return false;
    }

    public void makeMove(LinkedList<Card> deck, List<Player> players){

        boolean isGameOn = makeChoice();
        while (isGameOn){
            cards.add(deck.pop());
            int playerHand = getCardsValue();
            RenderScreen.showPlayerWindows(players);
            if(playerHand > 21) break;
            isGameOn = makeChoice();
        }
    }

    public boolean makeChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " please select your choice (H)it or (S)tand.");
        String choice = scanner.nextLine();

        return choice.toUpperCase().charAt(0) == 'H';
    }

    public int getMoney() {
        return money;
    }

    public int getBet() {
        return bet;
    }

    @Override
    public String showCards() {
        String result = "";
        for(Card card : cards){
            result += card.toString();
        }
        return result;
    }
}
