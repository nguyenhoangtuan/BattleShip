/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Lyn
 */
public class SideMenuFrame extends JPanel {

    private JButton but = new JButton("Nuclear Bomb");
    private JButton but1 = new JButton("Rain of bombs");
    private static JTextArea text = new JTextArea(8, 25);
    private static JLabel mana = new JLabel();
    private static String skills = "none";
    private JScrollPane sp = new JScrollPane(text);
    private JPanel pan = new JPanel();
    private JButton exit = new JButton("Exit");

    public SideMenuFrame() {
        pan.setLayout(new GridLayout(0, 1));
        pan.add(mana);
        mana.setText("       Energy : " + SeaPlayer.getMana() + "/10");
        pan.add(but);
        but.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (SeaPlayer.getMana() >= 8) {
                    skills = "bomb";
                } else {
                    text.append("need at least 8 energy to use this skill ! \n");
                }
            }
        });
        pan.add(but1);
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (SeaPlayer.getMana() >= 8) {
                    skills = "rain";
                } else {
                    text.append("need at least 8 energy to use this skill !\n");
                }
            }
        });
        pan.add(exit);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank for playing this game !!!");
                System.exit(0);
            }
        });
        add(pan);
        add(sp);
        text.setEditable(false);
        setPreferredSize(new Dimension(300, 300));
        setBorder(BorderFactory.createTitledBorder("Menu"));
    }

    public static void showMana() {
        mana.setText("       Energy : " + SeaPlayer.getMana() + "/10");
    }

    public static String getSkills() {
        return skills;
    }

    public static void setSkills(String skills) {
        SideMenuFrame.skills = skills;
    }

    public static void setText(String s) {
        text.append(s);
    }
}
