import players.HumanPlayer;
import players.Player;

import java.util.List;

public class RenderScreen {

    private static int blockWidth = 10;

    public static void showMenu(){

    }

    public static void showPlayerWindows(List<Player> players){
        renderLine(players);
        System.out.println();
        renderNamePart(players);
        System.out.println();
        renderLine(players);
        System.out.println();
        renderMoneyPart(players);
        System.out.println();
        renderLine(players);
        System.out.println();
        renderCardPart(players);
        System.out.println();
        renderLine(players);
    }

    private static void renderLine(List<Player> players){
        for (Player player : players){
            System.out.print("X");
            System.out.print("-".repeat(player.getName().length() + blockWidth*2));
            System.out.print("X");
            System.out.print(" ".repeat(5));
        }
    }

    private static void renderNamePart(List<Player> players){
        for(Player player : players) {
            //System.out.print("|");
            System.out.print("|" + " ".repeat(blockWidth));
            System.out.print(player.getName());
            System.out.print(" ".repeat(blockWidth) + "|");
            System.out.print(" ".repeat(5));
        }
    }

    private static void renderMoneyPart(List<Player> players){
        String balanceString = "| Balance: ";
        for(Player player : players){
            if(player instanceof HumanPlayer humanPlayer) {
                System.out.print(balanceString);
                String moneyS = String.valueOf(humanPlayer.getMoney());
                System.out.print(" ".repeat(blockWidth * 2 + player.getName().length() - balanceString.length() - moneyS.length() + 1));
                System.out.print(moneyS + "|");
            }else{
                System.out.print("|");
                System.out.print(" ".repeat(blockWidth*2 + player.getName().length()));
                System.out.print("|");
            }
            System.out.print(" ".repeat(5));
        }
    }

    private static void renderCardPart(List<Player> players){
        for(Player player : players){
            String cards = "|" + player.showCards();
            System.out.print(cards);
            System.out.print(" ".repeat(blockWidth*2 + player.getName().length() - cards.length() + 1));
            System.out.print("|");
            System.out.print(" ".repeat(5));
        }
    }

}