/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Models.SeaPlayerModel;
import Main.Main;
import Ship.ShipItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Lyn
 */
public class SeaPlayer extends JPanel implements Observer {

    BufferedImage img;
    private BufferedImage ship1;
    private static Cell[][] cells;
    private static int size;
    private static Random r = new Random();
    private ArrayList<ShipItem> ships = new ArrayList<ShipItem>();
    private SeaPlayerModel model;
    private static int mana;

    public SeaPlayer(int size, final SeaPlayerModel model) {
        this.model = model;
        SeaPlayer.size = size;
        this.ships = Main.getShips();
        setLayout(new GridLayout(size, size, 0, 0));
        final ShipAddFrame saf = new ShipAddFrame();
        cells = new Cell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                cells[x][y] = new Cell(x, y);
                add(cells[x][y]);
                final int i1 = x;
                final int j1 = y;
                cells[x][y].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if ("ok".equals(ShipAddFrame.getStatusAdd())) {
                            AllMap.addShipNew(AllMap.findCell(i1, j1, cells), saf.getIs(), "player", false);
                            model.change();
                        }
                    }
                });
            }
        }
        AllMap.loadCell();
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int length = Cell.getSizeCell();
        super.paintComponent(g);
        try {
            img = ImageIO.read(new File("seasea.jpg"));
        } catch (Exception e) {
            System.out.println("error");
        }
        g.drawImage(img, 0, 0, this);
        for (ShipItem items : ships) {
            if (items.getLocatedCell() != null) {
                try {
                    ship1 = ImageIO.read(new File(items.getLinks()));
                } catch (Exception e) {
                    System.out.println("error error");
                }
                g.drawImage(ship1, items.getLocatedCell().getXcell() * length + 8, length * items.getLocatedCell().getYcell() + 8, this);
            }
        }
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static int getMana() {
        return mana;
    }

    public void increaseMana(int mana) {
        model.increaseMana(mana);
    }

    public void decreaseMana(int mana) {
        model.decreaseMana(mana);
    }

    public static void setMana(int mana) {
        if (mana > 10) {
            SeaPlayer.mana = 10;
        } else {
            SeaPlayer.mana = mana;
        }
        SideMenuFrame.showMana();
    }

    public static void testAI() {
        int i = r.nextInt(10);
        int j = r.nextInt(10);
        if ("com".equals(Cell.getTurn())) {
            if (!"attacked".equals(cells[i][j].getStatus())) {
                cells[i][j].setToken('X');
            } else {
                testAI();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            mana = (Integer) arg;
            SideMenuFrame.showMana();
        }
        repaint();
    }

    public static void autoLocateShip(String s) {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        AllMap.addShipNew(cells[x][y], Main.findShipByName(s), "player", true);
    }

    public static void chooseShipLocation(String s) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Choose cell x ");
        int x = reader.nextInt();
        System.out.println("Choose cell y ");
        int y = reader.nextInt();
        System.out.println("Choose horizontal or vertical (H/V):");
        String option = reader.next();
        if ("h".equals(option) || "H".equals(option)) {
            AllMap.addShipNew(cells[x][y], Main.findShipByName(s), "player", false);
        } else {
            AllMap.addShipNew(cells[x][y], Main.findShipByName(s + "v"), "player", false);
        }
    }

    public void setModel() {
        model.change();
    }
}
