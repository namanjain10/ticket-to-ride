import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import manager.impl.BoardManagerImpl;
import manager.impl.GameManagerImpl;
import manager.impl.PlayerManagerImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoardManager boardManager = new BoardManagerImpl();
        PlayerManager playerManager = new PlayerManagerImpl();
        GameManager gameManager = new GameManagerImpl();


    }
}