/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Ship.ShipItem;
import Ship.ShipFile;
import Exception.UnreadableShipFileException;
import Frame.FirstPageFrame;
import java.util.ArrayList;

/**
 *
 * @author Lyn
 */
public class Main {

    private static ArrayList<ShipItem> ships = new ArrayList<ShipItem>();

    public static void main(String[] args) {
        Main m = new Main();
        m.loadData();
        FirstPageFrame fpf = new FirstPageFrame();
        m.saveData();
    }

    public void loadData() {
        try {
            ShipFile shipf = new ShipFile("ShipFile.txt");
            Main.ships = shipf.readFile();
        } catch (UnreadableShipFileException e) {
            System.out.println("Unreadable File Name");
        }
    }

    public void saveData() {
        try {
            ShipFile shipf = new ShipFile("ShipFile.txt");
            shipf.writeFile(ships);
        } catch (UnreadableShipFileException e) {
            System.out.println("Unreadable File Name");
        }
    }

    public static ArrayList<ShipItem> getShips() {
        return ships;
    }

    public static ShipItem findShipByName(String s) {
        for (ShipItem items : ships) {
            if (items.getName().equals(s)) {
                return items;
            }
        }
        System.out.println("No name match");
        return null;
    }

    public static void waitTime(int time) {
        long clock1;
        long clock2;
        clock1 = System.currentTimeMillis();
        do {
            clock2 = System.currentTimeMillis();
        } while ((clock2 - clock1) < time * 1000);
    }
}