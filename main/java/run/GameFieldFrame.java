package run;

import GameConfig.GameConfig;
import Graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 22.08.13
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class GameFieldFrame extends JPanel {
    private static GameFieldFrame ourInstance = new GameFieldFrame();
    private GameSession session = GameSession.getInstance();
    private String strategy;
    private Map<Integer, GameSession.User> users;

    public static GameFieldFrame getInstance() {
        return ourInstance;
    }

    public void resetGameFrame(String strategy) throws Exception {
        this.strategy = strategy;
        session.resetSession(strategy);
        session.setUsers(strategy);
        users = session.getUsers();
        removeAll();
        setElements();
    }

    private GameFieldFrame() {

        setBackground(new Color(45, 46, 49));
        setLayout(null);
        setSize(GameConfig.windowXSize, GameConfig.windowYSize);
    }

    private void setElements(){
        add(this.tablo());
        Integer i = 1;
        while(i<=GameConfig.countMatches){
            DrawMatche d = this.addMatche(i);
            session.addMatche(i, d);
            add(d);
            i++;
        }
        add(this.addBasket("white"));
        add(this.addBasket("black"));
        CountDisplay counter;
        for(i=1; i<=users.size(); i++){
            counter = users.get(i).getCounter();
            if(session.getCurrentUser().getId() == i){
                counter.setActive();
            }
            add(counter);
        }
        add(this.changeCurrentUser());
        HelpMessage helpMessage = new HelpMessage();
        session.addHelpMessage(helpMessage);
        add(helpMessage);
        helpMessage.setVisible(false);
        revalidate();
        repaint();
    }

    private JLabel tablo(){
        ShowTablo label = new ShowTablo(this.strategy);
        session.addTablo(label);
        return label;
    }

    private JButton changeCurrentUser(){
        ChangeUserButton button = new ChangeUserButton();
        session.addButton(button);
        return button;
    }

    private DrawBasket addBasket(String color){
        Map coordinates = GameConfig.basketCoordinates.get(color);
        DrawBasket d = new DrawBasket(color);
        d.setBounds((Integer) coordinates.get("x"), (Integer) coordinates.get("y"), (Integer) coordinates.get("xSize"), (Integer) coordinates.get("ySize"));
        d.setSize(GameConfig.xBoxSize,GameConfig.yBoxSize);
        return d;
    }

    private DrawMatche addMatche(Integer i){
        Random r = new Random();
        DrawMatche d = new DrawMatche(i);
        Integer x = Math.abs(r.nextInt(GameConfig.gameFieldXSize - GameConfig.barXSize) + (GameConfig.windowXSize - GameConfig.gameFieldXSize)/2);
        Integer y = Math.abs(r.nextInt(GameConfig.gameFieldYSize - GameConfig.barYSize) + (GameConfig.windowYSize - GameConfig.gameFieldYSize)/2);
        d.setBounds(x, y, GameConfig.barXSize, GameConfig.barRotateSize);
        //d.setSize(GameConfig.barXSize, GameConfig.barRotateSize);
        d.setBeginCoordinates(x, y);
        return d;
    }
}
