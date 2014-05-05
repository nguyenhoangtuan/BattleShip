/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Main.Main;
import Ship.ShipItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Lyn
 */
public class ShipAddFrame extends JFrame {

    ShipItem is;
    private JPanel pan = new JPanel();
    private JPanel panButton = new JPanel();
    private static String statusAdd = "no";
    private JButton btnStart = new JButton("Start");
    private JButton btnReset = new JButton("Reset");
    private JButton btnRotate = new JButton("Rotate");
    private JButton auto = new JButton("Auto");
    private ArrayList<ShipItem> ships = new ArrayList<ShipItem>();
    Point p = new Point();

    public ShipAddFrame() {
        this.setTitle("Add New Ship");
        this.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                p.setLocation(x, y);
            }
        });

        this.ships = Main.getShips();
        setLayout(new FlowLayout());
        pan.setPreferredSize(new Dimension(200, 200));
        pan.setBorder(new LineBorder(Color.black, 1));
        add(pan);
        pan.setLayout(new GridLayout(0, 1));
        final ShipDetail sd = new ShipDetail();
        for (int i = 0; i < 5; i++) {
            JButton but = new JButton(getShipName(i));
            pan.add(but);
            but.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    is = Main.findShipByName(((JButton) e.getSource()).getText());
                    if ("can".equals(is.getAdd())) {
                        statusAdd = "ok";
                    }
                }
            });
            but.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    sd.setLocation(p);
                    sd.init(Main.findShipByName(((JButton) e.getSource()).getText()));
                    sd.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    sd.setVisible(false);
                    sd.reset();
                }
            });
        }
        panButton.setLayout(new GridLayout(0, 1));
        panButton.add(btnRotate);
        btnRotate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("ok".equals(statusAdd)) {
                    if ((is.getName().charAt(is.getName().length() - 1)) != 'v') {
                        is = Main.findShipByName(is.getName() + "v");
                    } else {
                        is = Main.findShipByName(is.getName().substring(0, is.getName().length() - 1));
                    }
                }
            }
        });
        panButton.add(btnReset);
        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (ShipItem items : ships) {
                    items.reset();
                }
                AllMap.resetCellPlayer();
            }
        });
        panButton.add(btnStart);
        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                int countShip = 0;
                for (ShipItem items : ships) {
                    if ("cannot".equals(items.getAdd())) {
                        count++;
                    }
                    countShip++;
                }
                if (count == countShip) {
                    statusAdd = "no";
                    Cell.setClickable(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Need to add all Ships before starting !");
                }
            }
        });
        panButton.add(auto);
        auto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (ShipItem items : ships) {
                    items.reset();
                }
                AllMap.resetCellPlayer();
                SeaPlayer.autoLocateShip("battleship1");
                SeaPlayer.autoLocateShip("battleship2v");
                SeaPlayer.autoLocateShip("seawolfv");
                SeaPlayer.autoLocateShip("carrier");
                SeaPlayer.autoLocateShip("patrolv");
            }
        });
        add(panButton);
        pack();
        setVisible(true);
    }

    private String getShipName(int i) {
        if (i == 0) {
            return "patrol";
        } else if (i == 1) {
            return "battleship1";
        } else if (i == 2) {
            return "battleship2";
        } else if (i == 3) {
            return "seawolf";
        } else if (i == 4) {
            return "carrier";
        }
        return null;
    }

    public ShipItem getIs() {
        return is;
    }

    public void setIs(ShipItem is) {
        this.is = is;
    }

    public static String getStatusAdd() {
        return statusAdd;
    }

    public static void setStatusAdd(String statusAdd) {
        ShipAddFrame.statusAdd = statusAdd;
    }
}
