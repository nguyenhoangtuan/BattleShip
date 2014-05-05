/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Ship.ShipItem;
import Sounds.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lyn
 */
public class Cell extends JPanel {

    private static boolean clickable = false;
    private String status = "none";
    private BufferedImage image;
    private BufferedImage imageMiss;
    private BufferedImage imageba;
    private BufferedImage imageTarget;
    private char token = ' ';
    private char target = ' ';
    private static int cellsize = 40;
    private static String turn = "player";
    private String shipStatus = "NoShip";
    private int x;
    private int y;
    private static String previousHit = "miss";
    private String shipName = "none";

    public Cell(int x, int y) {
        setBorder(new LineBorder(Color.black, 1));
        setOpaque(false);
        this.x = x;
        this.y = y;
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setTarget('A');
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setTarget(' ');
            }
        });
    }

    public static int getSizeCell() {
        return cellsize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            imageTarget = ImageIO.read(new File("target.gif"));
            image = ImageIO.read(new File("fire.gif"));
            imageba = ImageIO.read(new File("water.gif"));
            imageMiss = ImageIO.read(new File("splash.gif"));
        } catch (IOException ex) {
            System.out.println("error");
        }
        super.paintComponent(g);
        if (shipStatus.equals("shipLocation")) {
  //          g.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
        }
        if (token == 'X') {
            if (this.shipStatus.equals("boom")) {
                g.drawImage(image, 0, 0, 38, 38, null);
            } else {
                g.drawImage(imageMiss, 0, 0, 38, 38, null);
            }
        }
        if (target == 'A') {
            g.drawImage(imageTarget, 0, 0, 38, 38, null);
        }
    }

    public static boolean isClickable() {
        return clickable;
    }

    public static void setClickable(boolean clickable) {
        Cell.clickable = clickable;
    }

    public void setTarget(char c) {
        target = c;
        repaint();
    }

    public void setToken(char c) {
        token = c;
        repaint();
        if ("none".equals(status)) {
            setStatus("attacked");
        }
        if ("shipLocation".equals(shipStatus)) {
            setShipStatus("boom");
            setPreviousHit("hit");
            setShipName("none");
            if (turn.equals("player")) {
                SideMenuFrame.setText("HIT!! You hit the enemy ship !!!!! \n");
                Sound.playSound("explosion.wav");
            }
        } else {
            setPreviousHit("miss");
            if (turn.equals("player")) {
                SideMenuFrame.setText("There is nothing at your target .... \n");
                Sound.playSound("bomb.wav");
            }
        }
        AllMap.isWin();
        ShipItem shipTest = AllMap.checkShipAtCell("computer");
        if (shipTest != null) {
            if (turn.equals("player")) {
                SideMenuFrame.setText(shipTest.getName() + " of Computer is boomed. \n");
            }
        }
        if (turn.equals("player") && previousHit.equals("miss")) {
            turn = "com";
            SeaPlayer.testAI();
            SeaPlayer.setMana(SeaPlayer.getMana() + 1);
        } else if (turn.equals("com") && previousHit.equals("hit")) {
            SeaPlayer.testAI();
        } else if (turn.equals("com") && previousHit.equals("miss")) {
            turn = "player";
        } else if (turn.equals("player") && previousHit.equals("hit")) {
            SeaPlayer.setMana(SeaPlayer.getMana() + 1);
        }
    }

    public void setTokenSpecial(char c) {
        token = c;
        repaint();
        if ("none".equals(status)) {
            setStatus("attacked");
        }
        if ("shipLocation".equals(shipStatus)) {
            setShipStatus("boom");
            setShipName("none");
        }
        AllMap.isWin();
        ShipItem shipTest = AllMap.checkShipAtCell("computer");
        if (shipTest != null) {
            SideMenuFrame.setText(shipTest.getName() + " of Computer is boomed. \n");
        }
    }

    public static String getTurn() {
        return turn;
    }

    public static void setTurn(String turn) {
        Cell.turn = turn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(String shipStatus) {
        this.shipStatus = shipStatus;
    }

    public String getXY() {
        return "x = " + x + "  y = " + y;
    }

    public int getXcell() {
        return x;
    }

    public int getYcell() {
        return y;
    }

    public static String getPreviousHit() {
        return previousHit;
    }

    public static void setPreviousHit(String previousHit) {
        Cell.previousHit = previousHit;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}