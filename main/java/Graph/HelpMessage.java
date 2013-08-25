package Graph;

import GameConfig.GameConfig;
import run.GameSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 24.08.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class HelpMessage extends JLabel{

    public HelpMessage(){
        setText("<html>there will be a help message");
        setSize(GameConfig.helpMessageXSize, GameConfig.helpMessageYSize);
        setForeground(Color.darkGray);
        setBackground(Color.lightGray);
        JButton button = new JButton();
        Integer x = GameConfig.windowXSize/2-50;
        button.setBounds(x, 40, 100, 20);
        button.setMargin(new Insets(3, 2, 3, 2));
        button.setBorderPainted(false);
        setText("передать ход");
        setBackground(Color.gray);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
    }
}
