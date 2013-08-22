package Graph;

import GameConfig.GameConfig;
import run.GameSession;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 23.08.13
 * Time: 0:55
 * To change this template use File | Settings | File Templates.
 */
public class CountDisplay extends JLabel {

    public CountDisplay(String color, String type){
        Map coordinates = GameConfig.basketCoordinates.get(color);
        setBounds((Integer)coordinates.get("counterX"), (Integer)coordinates.get("counterY"), 150, 50);
        setSize(150, 50);
        setCount(type, 10);
        setForeground(Color.darkGray);
    }

    public void setActive(){
        setForeground(Color.cyan);
    }

    public void setCount(String type, Integer count){
        if(type.equals("human")){
            setText("<html>Вы можете добавить<br>себе ещё " + count + " спичек");
        } else {
            setText("<html>Ход компьютера,<br>возможно взять " + count + "спичек");
        }
    }
}
