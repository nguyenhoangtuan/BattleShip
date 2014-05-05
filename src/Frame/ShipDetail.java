/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Ship.ShipItem;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Lyn
 */
public class ShipDetail extends JFrame {

    JLabel ship;
    JLabel la1 = new JLabel();
    JLabel la2 = new JLabel();
    JLabel la3 = new JLabel();
    JLabel la4 = new JLabel();
    JLabel la5 = new JLabel();
    JLabel lab = new JLabel("****Ship Information****");
    JPanel pan = new JPanel();

    public ShipDetail() {
        this.setTitle("Ship Detail");
    }

    public void init(ShipItem si) {
        setLayout(new FlowLayout());
        pan.setLayout(new GridLayout(0, 1));
        String name = si.getName();
        la1.setText("Ship Name :  " + si.getName());
        la2.setText("Size Horizontal : " + si.getSizeShipH());
        la3.setText("Size Vertival       : " + si.getSizeShipV());
        la4.setText("Add Available : " + si.getAdd());
        if ("battleship1".equals(name) || "battleship2".equals(name)) {
            ship = new JLabel(new ImageIcon(getClass().getResource("/Images/battleship.gif")));
        } else if ("carrier".equals(name)) {
            ship = new JLabel(new ImageIcon(getClass().getResource("/Images/carrier.gif")));
        } else if ("patrol".equals(name)) {
            ship = new JLabel(new ImageIcon(getClass().getResource("/Images/patrol.gif")));
        } else {
            ship = new JLabel(new ImageIcon(getClass().getResource("/Images/seawolf.gif")));
        }
        pan.add(ship);
        pan.add(la1);
        pan.add(la2);
        pan.add(la3);
        pan.add(la4);
        pan.setBorder(BorderFactory.createTitledBorder("Ship Information"));
        add(pan);
        pack();
    }

    public void reset() {
        pan.removeAll();
        pan.repaint();
        pan.validate();
    }
}
