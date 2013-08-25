package Graph;

import run.GameFieldFrame;
import run.GameSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 20.08.13
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public class UserInterface {

    GameSession session = GameSession.getInstance();
    GameFieldFrame lp = GameFieldFrame.getInstance();

    public final JMenuBar getMenu() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon(getClass().getResource("../exit.png"));

        JMenu file = new JMenu("Игра");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem select1 = new JMenuItem("Человек - человек");
        select1.setMnemonic(KeyEvent.VK_S);
        select1.setToolTipText("human to human strategy select");
        select1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    lp.resetGameFrame("userToUser");
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        JMenuItem select2 = new JMenuItem("Человек - робот");
        select2.setMnemonic(KeyEvent.VK_S);
        select2.setToolTipText("human to robot strategy select");
        select2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    lp.resetGameFrame("userToRobot");
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        JMenuItem select3 = new JMenuItem("Робот - робот");
        select3.setMnemonic(KeyEvent.VK_S);
        select3.setToolTipText("robot to robot strategy select");
        select3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    lp.resetGameFrame("robotToRobot");
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        JMenuItem help = new JMenuItem("Помощь");
        select3.setMnemonic(KeyEvent.VK_S);
        select3.setToolTipText("правила, подсказки и т.д.");
        select3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    HelpMessage helpMessage = session.getHelpMessage();
                    helpMessage.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        JMenuItem eMenuItem = new JMenuItem("выход");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(select1);
        file.add(select2);
        file.add(select3);
        file.add(help);
        file.add(eMenuItem);
        menubar.add(file);

        return menubar;
    }

}
