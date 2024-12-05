import players.HumanPlayer;
import players.Player;

public class Main {
    public static void main(String[] args) {

        BlackJack blackJack = new BlackJack(1,"Fero");

        Player player = new HumanPlayer("Miro", 10000);
        blackJack.addPlayer(player);

        blackJack.startGame();
    }
}
