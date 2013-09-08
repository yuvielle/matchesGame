package Graph;

import GameConfig.GameConfig;
import run.GameSession;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NavigableMap;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 24.08.13
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class HelpMessage extends JLabel{

    public HelpMessage(){
        setText("<html><h2>Правила игры</h2><p>Из кучки, первоначально содержащей 100 спичек,<br>двое играющих поочередно берут по несколько спичек: не более десяти. <br>Выигрывает взявший последнюю спичку. предусмотрена возможность игры человек-человек, человек-машина, машина-машина.</p>" +
                "<p>В меню игра Вы можете выбрать стратегию игры: человек-человек, человек-машина или машина-машина</p><p>По умолчанию выбран вариант человек-человек</p><p>Чтоб выйти из этой справки и начать игру нажмите кнопку \"закрыть\" в низу этого окна.</p>");
        setForeground(Color.darkGray);
        setBackground(Color.lightGray);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(true);
        setBounds(GameConfig.helpMessageX, GameConfig.helpMessageY, GameConfig.helpMessageXSize, GameConfig.helpMessageYSize);

        JButton button = new JButton();
        Integer x = GameConfig.windowXSize/2-GameConfig.helpMessageX-20;
        Integer y = 260;
        button.setBounds(x, y, 100, 20);
        button.setMargin(new Insets(3, 2, 3, 2));
        button.setBorderPainted(false);
        button.setText("закрыть");
        add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    setVisible(false);
                    GameSession session = GameSession.getInstance();
                    NavigableMap<Integer, DrawMatche> matches = session.getAllMatches();
                    for(int i = 1; i<=matches.size(); i++){
                        matches.get(i).setVisible(true);
                        matches.get(i).repaint();
                    }

                    session.getButton().setVisible(true);
                    session.getButton().repaint();

                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
    }
}
