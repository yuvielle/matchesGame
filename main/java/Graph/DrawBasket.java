package Graph;

import GameConfig.GameConfig;
import run.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 22.08.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public class DrawBasket  extends JComponent {

    private TexturePaint texture;
    protected Point anchorPoint;
    private String color;
    private Map coordinates;

    public DrawBasket(String color){
        addMouseListeners();
        this.color = color;
        setOpaque(true);
        setBackground(new Color(45, 46, 49));
        BufferedImage randomTexture = this.loadImages();
        coordinates = GameConfig.basketCoordinates.get(color);
        this.texture = new TexturePaint(randomTexture, new Rectangle(0, 0, (Integer) coordinates.get("xSize"), (Integer) coordinates.get("ySize")));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(this.texture);
        g2d.fillRect(0, 0, (Integer) coordinates.get("xSize"), (Integer) coordinates.get("ySize"));
    }

    private void addMouseListeners() {
        /** This handle is a reference to THIS because in next Mouse Adapter
         "this" is not allowed */
        final DrawBasket handle = this;
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }

    private BufferedImage loadImages() {

        try {
            Random r = new Random();
            Integer num = Math.abs(r.nextInt(2) + 1);
            Map collection = GameConfig.basketTextureNames.get(this.color);
            String name = (String) collection.get(num);
            BufferedImage image = ImageIO.read(this.getClass().getResource(name));
            return image;
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void removeMouseListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }
}
