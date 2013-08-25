package Graph;

import GameConfig.GameConfig;
import run.GameSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 24.08.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class ChangeUserButton extends JButton {

    private boolean active = true;
    private GameSession session = GameSession.getInstance();
    private boolean is_new = true;
    public ChangeUserButton(){
        addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!active) return;
                try {
                    Integer available = session.getAviableMatches();

                    setText("передать ход");

                    if(!is_new) session.changeCurrentUser();
                    else is_new = false;

                    session.getCurrentUser().resetCount(available);
                    if (session.getCurrentUser().getType().equals("robot")) {
                        robotTurn();
                    }
                    repaint();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.out.println("available matches=" + session.getAviableMatches());
            }
        });
        Integer x = GameConfig.windowXSize/2-50;
        setBounds(x, 40, 100, 20);
        setMargin(new Insets(3, 2, 3, 2));
        setBorderPainted(false);
        setText("Начать");
    }

    public boolean is_new(){
        return this.is_new;
    }

    public void setInactive(){
        setForeground(Color.lightGray);
        active = false;
    }

    public void setActive(){
        setForeground(Color.black);
        active = true;
    }
    private void robotTurn() throws Exception {
        if(session.getAviableMatches() <= 0){
            throw new Exception("no matches for this turn");
        }
        else if(session.getAviableMatches() <= 10){
            this.takeMatches(session.getAviableMatches());
        }
        else{
            Random r = new Random();
            Integer m = Math.abs(r.nextInt(9)+1);
            this.takeMatches(m);
            System.out.println("select matches=" + m);
        }
        if(session.getNextUser().getType().equals("robot")){
            session.changeCurrentUser();
            Integer available = session.getAviableMatches();
            session.getCurrentUser().resetCount(available);
            this.robotTurn();
        }
        else if(session.getAviableMatches() > 0){
            session.changeCurrentUser();
        }
    }

    private void takeMatches(int count){
        String color = session.getCurrentUser().getColor();
        for(int i=1; i<=count; i++){
            System.out.println("take matche=" + i + "\n");
            DrawMatche matche = session.getLastMatche();
            matche.move(color);
            session.remooveMatche(matche.getId());
        }
    }
}
