/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Lyn
 */
public class FirstPageFrame extends JFrame {

    public FirstPageFrame() {
        this.setTitle("Loading Page");
        setLayout(new FlowLayout());
        FirstPage fp = new FirstPage(this);
        add(fp);
        setSize(500, 450);
        setVisible(true);
    }
}
