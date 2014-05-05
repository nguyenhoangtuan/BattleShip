/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Lyn
 */
public class FirstPage extends JPanel {

    private JButton but1 = new JButton("Start New Game");
    private JButton but3 = new JButton("Exit");
    private JPanel pan = new JPanel();
    private BufferedImage img;
    private FirstPageFrame fpf;

    public FirstPage(final FirstPageFrame fpf) {
        this.fpf = fpf;
        setLayout(new FlowLayout());
        pan.add(but1);
        but1.setLocation(250, 250);
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AllMap am = new AllMap(10);
                am.setLocation(0, 250);
                fpf.setVisible(false);
            }
        });
        pan.add(but3);
        but3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(pan);
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            img = ImageIO.read(new File("wallpaper.jpg"));
        } catch (Exception e) {
            System.out.println("error");
        }
        g.drawImage(img, 0, 0, this);
    }
}
