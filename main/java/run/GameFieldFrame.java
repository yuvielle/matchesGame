package run;

import GameConfig.GameConfig;
import Graph.CountDisplay;
import Graph.DrawBasket;
import Graph.DrawMatche;

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
        session.resetSession();
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
        while(i<=100){
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
        revalidate();
        repaint();
    }

    private JLabel tablo(){
        JLabel label = new JLabel();
        label.setText("выбран вариант: " + GameConfig.strategyTitles.get(this.strategy));
        label.setSize(300, 50);
        label.setForeground(Color.cyan);
        return label;
    }

    private JButton changeCurrentUser(){
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                try {
                    Integer available = session.getAviableMatches();

                    session.changeCurrentUser();
                    session.getCurrentUser().resetCount(available);
                    repaint();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("available matches=" + session.getAviableMatches());
            }
        });
        button.setSize(50, 20);
        button.setBounds(300,10,50,20);
        button.setText("передать ход");
        button.setBackground(Color.gray);
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
        d.setBounds(x, y, GameConfig.barXSize, GameConfig.barYSize);
        d.setSize(GameConfig.barXSize, GameConfig.barXSize);
        return d;
    }
}
