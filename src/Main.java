import players.HumanPlayer;
import players.Player;

public class Main {
    public static void main(String[] args) {

        BlackJack blackJack = new BlackJack(1);

        Player player = new HumanPlayer("Miro", 100);
        Player player1 = new HumanPlayer("Lukas", 100);
        blackJack.addPlayer(player);
        blackJack.addPlayer(player1);

        blackJack.startGame();
    }
}
