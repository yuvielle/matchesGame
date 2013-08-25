package Graph;

import GameConfig.GameConfig;
import run.GameSession;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 24.08.13
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class ShowTablo extends JLabel {
    private String strategy;
    private GameSession session = GameSession.getInstance();

    public ShowTablo(String strategy){
        this.strategy = strategy;
        setText("выбран вариант: " + GameConfig.strategyTitles.get(this.strategy));
        setSize(350, 50);
        setForeground(Color.cyan);
    }

    public void setWinners(){
        GameSession.User user = session.getCurrentUser();
        setText("Игра завершена, победиель " + user.getType() + " игрок " + user.getColor());
    }

    public void resetTablo(String strategy){
        this.strategy = strategy;
        setText("выбран вариант: " + GameConfig.strategyTitles.get(this.strategy));
    }
}
