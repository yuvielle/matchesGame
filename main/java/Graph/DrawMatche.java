package Graph;

import GameConfig.GameConfig;
import run.GameSession;
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
 * Date: 20.08.13
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class DrawMatche extends JComponent {

    /** If sets <b>TRUE</b> this component is draggable */
    private boolean draggable = true;
    /** 2D Point representing the coordinate where mouse is, relative parent container */
    protected Point anchorPoint;
    /** Default mouse cursor for dragging action */
    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    /** If sets <b>TRUE</b> when dragging component,
     it will be painted over each other (z-Buffer change) */
    protected boolean overbearing = false;
    private TexturePaint texture;
    private boolean active = true;
    private GameSession session = GameSession.getInstance();
    private Integer id;

    public DrawMatche(Integer id) {
        this.id = id;
        addDragListeners();
        setOpaque(true);
        setBackground(new Color(45, 46, 49));
        BufferedImage randomTexture = this.loadImages();
        this.texture = new TexturePaint(randomTexture, new Rectangle(0, 0, GameConfig.barXSize, GameConfig.barYSize));
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Random r = new Random();
        Integer rotateDegree = Math.abs(r.nextInt(20));
        g2d.rotate(Math.toRadians(rotateDegree));
        g2d.setPaint(this.texture);
        g2d.fillRect(0, 0, GameConfig.barXSize, GameConfig.barYSize);
    }

    /**
     * Add Mouse Motion Listener with drag function
     */
    private void addDragListeners() {
        /** This handle is a reference to THIS because in next Mouse Adapter
         "this" is not allowed */
        final DrawMatche handle = this;
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;
                GameSession.User user = session.getCurrentUser();
                Integer count = user.getCount();
                if(count <= 0) return;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x -
                        anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(position);

                //Change Z-Buffer if it is "overbearing"
                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }

                String color = user.getColor();
                Map basketCoordinates = GameConfig.basketCoordinates.get(color);
                Integer boxX = (Integer) basketCoordinates.get("x");
                Integer boxY = (Integer) basketCoordinates.get("y");
                Integer basketWith = (Integer) basketCoordinates.get("xSize");

                if(((color.equals("white") && getLocation().getX() > boxX)|| (color.equals("black") && getLocation().getX() < (boxX + basketWith))) && getLocation().getY() > boxY){
                    active = false;
                    removeDragListeners();
                    session.remooveMatche(id);
                    user.updateCount();
                    CountDisplay counter = user.getCounter();
                    counter.setCount("human", user.getCount());
                    counter.repaint();
                }
            }
        });
    }

    public boolean getActive(){
        return active;
    }

    private BufferedImage loadImages() {

        try {
            Random r = new Random();
            Integer num = Math.abs(r.nextInt(4) + 1);
            String name = GameConfig.textureNames.get(num);
            BufferedImage image = ImageIO.read(this.getClass().getResource(name));
            return image;
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Get the value of overbearing
     *
     * @return the value of overbearing
     */
    public boolean isOverbearing() {
        return overbearing;
    }

    /**
     * Set the value of overbearing
     *
     * @param overbearing new value of overbearing
     */
    public void setOverbearing(boolean overbearing) {
        this.overbearing = overbearing;
    }
}
