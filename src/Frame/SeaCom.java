/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Models.SeaComModel;
import Main.Main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Lyn
 */
public class SeaCom extends JPanel implements Observer {

    private static Random r = new Random();
    private static Cell[][] cellsAI;
    private BufferedImage img;
    private SeaComModel model;

    public SeaCom(int size, final SeaComModel model) {
        this.model = model;
        setLayout(new GridLayout(size, size, 0, 0));
        cellsAI = new Cell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                final int i = x;
                final int j = y;
                cellsAI[x][y] = new Cell(x, y);
                add(cellsAI[x][y]);
                cellsAI[x][y].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Cell.isClickable()) {
                            if ("player".equals(Cell.getTurn())) {
                                if (!"attacked".equals(cellsAI[i][j].getStatus())) {
                                    cellsAI[i][j].setToken('X');
                                }
                            }
                            if (SideMenuFrame.getSkills().equals("bomb")) {
                                model.nuclearSkill(cellsAI, i, j);
                                SeaPlayer.setMana(SeaPlayer.getMana() - 8);
                                SideMenuFrame.setSkills("none");
                            }
                            if (SideMenuFrame.getSkills().equals("rain")) {
                                model.rainSkill(cellsAI, i, j);
                                SeaPlayer.setMana(SeaPlayer.getMana() - 8);
                                SideMenuFrame.setSkills("none");
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (SideMenuFrame.getSkills().equals("bomb")) {
                            model.nuclearTarget(cellsAI, i, j);
                        }

                        if (SideMenuFrame.getSkills().equals("rain")) {
                            model.rainTarget(cellsAI, i, j);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        model.clearTarget(cellsAI, i, j);
                    }
                });
            }
        }
        AllMap.loadCell();
        if (r.nextInt(2) == 1) {
            autoLocateShip("battleship1");
        } else {
            autoLocateShip("battleship1v");
        }
        if (r.nextInt(2) == 1) {
            autoLocateShip("seawolf");
        } else {
            autoLocateShip("seawolfv");
        }
        if (r.nextInt(2) == 1) {
            autoLocateShip("carrier");
        } else {
            autoLocateShip("carrierv");
        }
        if (r.nextInt(2) == 1) {
            autoLocateShip("patrol");
        } else {
            autoLocateShip("patrolv");
        }
        if (r.nextInt(2) == 1) {
            autoLocateShip("battleship2");
        } else {
            autoLocateShip("battleship2v");
        }
        setPreferredSize(new Dimension(400, 400));
    }

    public static Cell[][] getCellsAI() {
        return cellsAI;
    }

    public static void autoLocateShip(String s) {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        AllMap.addShipNew(cellsAI[x][y], Main.findShipByName(s), "computer", true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            img = ImageIO.read(new File("seasea.jpg"));
        } catch (Exception e) {
            System.out.println("error");
        }
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    public void setModel() {
        model.change();
    }
}
