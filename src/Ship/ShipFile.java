/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ship;

import Exception.UnreadableShipFileException;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lyn
 */
public class ShipFile {

    private String fileName;

    public ShipFile(String fileName) throws UnreadableShipFileException {
        this.fileName = fileName;
        if (fileName.indexOf(".txt") < 0) {
            throw new UnreadableShipFileException("broken file name");
        }
    }

    public void writeFile(ArrayList<ShipItem> ships) {
        try {
            File fl = new File(fileName);
            Writer w = new PrintWriter(fl);
            for (ShipItem item : ships) {
                w.write(item.outPutString() + "\n");
            }
            w.close();
        } catch (Exception e) {
        }
    }

    public ArrayList<ShipItem> readFile() {
        ArrayList<ShipItem> ships = new ArrayList<ShipItem>();
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {

                ShipItem m = new ShipItem(sc.nextLine());
                ships.add(m);
            }
            sc.close();
        } catch (Exception e) {
        }

        return ships;
    }
}
