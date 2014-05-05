/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Models.SeaPlayerModel;
import Models.SeaComModel;
import Main.Main;
import Ship.ShipItem;
import Sounds.Sound;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Lyn
 */
public class AllMap extends JFrame {

    private static int size;
    private static Cell[][] cells;
    private static Cell[][] cellsAI;
    private static SeaPlayer sc;
    private static ArrayList<ShipItem> ships = new ArrayList<ShipItem>();

    public AllMap(int size) {
        this.setTitle("Game");
        AllMap.ships = Main.getShips();
        AllMap.size = size;
        setLayout(new FlowLayout());
        SideMenuFrame smf = new SideMenuFrame();
        add(smf);

        SeaPlayerModel rm = new SeaPlayerModel();
        sc = new SeaPlayer(size, rm);
        rm.addObserver(sc);

        JPanel wrap1 = new JPanel();
        wrap1.add(sc);
        wrap1.setBorder(BorderFactory.createTitledBorder("Player Field"));
        add(wrap1);

        add(new JLabel("<===>"));
        JPanel wrap2 = new JPanel();

        SeaComModel scm = new SeaComModel();
        SeaCom scom = new SeaCom(size, scm);
        scm.addObserver(scom);

        wrap2.add(scom);
        add(wrap2);
        wrap2.setBorder(BorderFactory.createTitledBorder("Computer Field"));

        pack();
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        AllMap.cells = SeaPlayer.getCells();
        AllMap.cellsAI = SeaCom.getCellsAI();
    }

    public static void loadCell() {
        AllMap.cells = SeaPlayer.getCells();
        AllMap.cellsAI = SeaCom.getCellsAI();
    }

    public static void addShipIntoMap(Cell c) {
        c.setShipStatus("shipLocation");

    }

    public static void addShipIntoMap(Cell c, ShipItem si) {
        c.setShipStatus("shipLocation");
        if ((si.getName().charAt(si.getName().length() - 1)) == 'v') {
            c.setShipName(si.getName().substring(0, si.getName().length() - 1));
        } else {
            c.setShipName(si.getName());
        }
    }

    public static int getSizeMap() {
        return size;
    }

    public static void isWin() {
        int countPlayer = 0;
        int countCom = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if ("shipLocation".equals(cells[x][y].getShipStatus())) {
                    countPlayer++;
                }
                if ("shipLocation".equals(cellsAI[x][y].getShipStatus())) {
                    countCom++;
                }
            }
        }
        if (countPlayer == 0) {
            JOptionPane.showMessageDialog(null, "Too bad, you lose, Thanks for playing this game");
            System.exit(0);
        } else if (countCom == 0) {
            Sound.playSound("victorious.wav");
            JOptionPane.showMessageDialog(null, "You win, Thanks for playing this game");
            System.exit(0);
        }
    }

    public static Cell findCellByXY(int x, int y, ArrayList<Cell> c) {
        for (Cell item : c) {
            if (item.getXcell() == x && item.getYcell() == y) {
                return item;
            }
        }
        return null;
    }

    public static Cell findCell(int a, int b, Cell[][] c) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (c[x][y].getXcell() == a && c[x][y].getYcell() == b) {
                    return c[x][y];
                }
            }
        }
        System.out.println("can not find the sell");
        return null;
    }

    public static void addShipNew(Cell c, ShipItem si, String s, boolean auto) {
        int x = c.getXcell();
        int y = c.getYcell();
        int sizeH = si.getSizeShipH();
        int sizeV = si.getSizeShipV();
        if (((size - sizeH - x) < 0 || (size - sizeV - y) < 0) && s.equals("computer")) {
            SeaCom.autoLocateShip(si.getName());
        } else if (((size - sizeH - x) < 0 || (size - sizeV - y) < 0) && s.equals("player")) {
            SideMenuFrame.setText("can not add at playery field  \n");
            if (auto) {
                SeaPlayer.autoLocateShip(si.getName());
            }
        } else {
            if (s.equals("player")) {
                int countSizeH = 0;
                int countSizeV = 0;
                for (int i = 0; i < sizeH; i++) {
                    if (!"shipLocation".equals(findCell(x + i, y, cells).getShipStatus())) {
                        countSizeH++;
                    }
                }
                for (int i = 0; i < sizeV; i++) {
                    if (!"shipLocation".equals(findCell(x, y + i, cells).getShipStatus())) {
                        countSizeV++;
                    }
                }
                if (countSizeH == sizeH && countSizeV == sizeV) {
                    for (int i = 0; i < sizeH; i++) {
                        addShipIntoMap(findCell(x + i, y, cells), si);
                    }
                    for (int i = 0; i < sizeV; i++) {
                        addShipIntoMap(findCell(x, y + i, cells), si);
                    }
                    si.setLocatedCell(c);
                    setNotAvailable(si);
                    ShipAddFrame.setStatusAdd("no");
                } else {
                    SideMenuFrame.setText("There is another ship already in player field \n");
                    if (auto) {
                        SeaPlayer.autoLocateShip(si.getName());
                    }
                }
            } else {
                int countSizeH = 0;
                int countSizeV = 0;
                for (int i = 0; i < sizeH; i++) {
                    if (!"shipLocation".equals(findCell(x + i, y, cellsAI).getShipStatus())) {
                        countSizeH++;
                    }
                }
                for (int i = 0; i < sizeV; i++) {
                    if (!"shipLocation".equals(findCell(x, y + i, cellsAI).getShipStatus())) {
                        countSizeV++;
                    }
                }
                if (countSizeH == sizeH && countSizeV == sizeV) {
                    for (int i = 0; i < sizeH; i++) {
                        addShipIntoMap(findCell(x + i, y, cellsAI), si);
                    }
                    for (int i = 0; i < sizeV; i++) {
                        addShipIntoMap(findCell(x, y + i, cellsAI), si);
                    }
                } else {
                    SeaCom.autoLocateShip(si.getName());
                }
            }
        }
    }

    public static void setNotAvailable(ShipItem is) {
        is.setAdd("cannot");
        if ((is.getName().charAt(is.getName().length() - 1)) != 'v') {
            is = Main.findShipByName(is.getName() + "v");
            is.setAdd("cannot");
        } else {
            is = Main.findShipByName(is.getName().substring(0, is.getName().length() - 1));
            is.setAdd("cannot");
        }
    }

    public static void resetCellPlayer() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                cells[x][y].setShipStatus("NoShip");
            }
        }
        sc.setModel();
    }

    public static ShipItem checkShipAtCell(String s) {
        if (s.equals("computer")) {
            for (ShipItem items : ships) {
                int count = 0;
                if (items.getName().charAt(items.getName().length() - 1) != 'v') {
                    for (int y = 0; y < size; y++) {
                        for (int x = 0; x < size; x++) {
                            if (cellsAI[x][y].getShipName().equals(items.getName())) {
                                count++;
                            }
                        }
                    }
                    if (count == 0) {
                        ships.remove(items);
                        return items;
                    }

                }
            }
        }

        return null;
    }

    public static void test() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (!cellsAI[x][y].getShipName().equals("none")) {
                }
            }
        }
    }
}
