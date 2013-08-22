package run; /**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 20.08.13
 * Time: 12:26
 * To change this template use File | Settings | File Templates.
 */
import GameConfig.GameConfig;
import Graph.DrawBasket;
import Graph.DrawMatche;
import Graph.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainFrame extends JFrame {

    public MainFrame(){

        UserInterface interfaceObject = new UserInterface();
        JMenuBar menubar = interfaceObject.getMenu();

        this.setJMenuBar(menubar);
        setTitle("100 Спичек");
        setSize(GameConfig.windowXSize, GameConfig.windowYSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JPanel setGameField() throws Exception {
        GameFieldFrame lp = GameFieldFrame.getInstance();
        lp.resetGameFrame("userToUser");
        return lp;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame game = new MainFrame();
                try {
                    game.add(game.setGameField());
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                game.setVisible(true);
            }
        });
    }
}
