package players;

import cards.Card;

import java.util.Scanner;

public class HumanPlayer extends Player{

    private int money;
    private int bet;

    public HumanPlayer(String name, int money) {
        super(name);
        this.money = money;
        bet = 0;
    }

    public void addMoneyWon(int moneyWon){
        money += moneyWon;
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

    public char makeChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(name + " please select your choice (h)it or (s)tand.");
        String choice = scanner.nextLine();

        return choice.toUpperCase().charAt(0);
    }

    public int getMoney() {
        return money;
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
